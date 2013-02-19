/**
 * Copyright (C) 2013 Salzburg Research.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.newmedialab.lmf.security.services;

import at.newmedialab.lmf.security.api.SecurityService;
import at.newmedialab.lmf.security.model.SecurityConstraint;
import at.newmedialab.lmf.security.util.SubnetInfo;
import com.google.common.collect.Lists;
import kiwi.core.api.config.ConfigurationService;
import kiwi.core.events.ConfigurationChangedEvent;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import sun.net.util.IPAddressUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static at.newmedialab.lmf.security.model.HTTPMethods.parse;

/**
 * Security Service default implementartion
 * 
 * @author Sebastian Schaffert
 */
@ApplicationScoped
public class SecurityServiceImpl implements SecurityService {

    @Inject
    private Logger log;

    @Inject
    private ConfigurationService configurationService;

    private boolean profileLoading = false;

    private List<SecurityConstraint> constraints;

    @PostConstruct
    public void initialise() {
        log.info("Initialising Security Service;  Access control is {}.",configurationService.getBooleanConfiguration("security.enabled",true)?"enabled":"disabled");
        initSecurityConstraints();
    }

    /**
     * Parse the security configuration contained in the configuration file into a list of SecurityConstraints, ordered
     * by priority. This list will be evaluated for each request to the system.
     */
    private void initSecurityConstraints() {
        constraints = new ArrayList<SecurityConstraint>();

        if(configurationService.getBooleanConfiguration("security.enabled",true)) {

            for(String type : Lists.newArrayList("permission","restriction")) {
                // determine the names of constraints that are configured
                Set<String> configNames = new HashSet<String>();
                for(String key : configurationService.listConfigurationKeys("security."+type)) {
                    String[] components = key.split("\\.");
                    if(components.length > 2) {
                        configNames.add(components[2]);
                    }
                }

                for(String configName : configNames) {
                    String keyPrefix = "security."+type+"."+configName;

                    String pattern = configurationService.getStringConfiguration(keyPrefix+".pattern");
                    boolean enabled = configurationService.getBooleanConfiguration(keyPrefix + ".enabled", true);
                    int priority = configurationService.getIntConfiguration(keyPrefix+".priority",1);
                    List<String> methods = configurationService.getListConfiguration(keyPrefix + ".methods");
                    List<String> hosts = configurationService.getListConfiguration(keyPrefix + ".host");
                    List<String> roles = configurationService.getListConfiguration(keyPrefix + ".roles");

                    SecurityConstraint constraint =
                            new SecurityConstraint(SecurityConstraint.Type.valueOf(type.toUpperCase()),
                                    configName,
                                    pattern,
                                    enabled,
                                    priority);
                    constraint.getRoles().addAll(roles);

                    for(String method : methods) {
                        constraint.getMethods().add(parse(method));
                    }

                    constraint.setHostPatterns(parseHostAddresses(hosts));
                    constraints.add(constraint);

                }
            }

            Collections.sort(constraints);

            if(log.isInfoEnabled()) {
                log.info("The following security constraints have been configured:");
                for(SecurityConstraint constraint : constraints) {
                    log.info("-- {}",constraint.toString());
                }

            }
        }
    }

    /**
     * Parse host patterns into subnet information. A host pattern has one of the following forms:
     * <ul>
     *     <li>LOCAL, meaning all local interfaces</li>
     *     <li>x.x.x.x/yy, meaning an IPv4 CIDR address with netmask (number of bits significant for the network, max 32), e.g. 192.168.100.0/24 </li>
     *     <li>xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx/yy, meaning an IPv6 CIDR address with netmask (prefix length, max 128)</li>
     * </ul>
     * @param hostPatternStrings
     * @return
     */
    private Set<SubnetInfo> parseHostAddresses(List<String> hostPatternStrings) {
        HashSet<SubnetInfo> hostPatterns = new HashSet<SubnetInfo>();
        for(String host : hostPatternStrings) {
            try {
                // reserved name: LOCAL maps to all local addresses
                if("LOCAL".equalsIgnoreCase(host)) {
                    try {
                        Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
                        while(ifs.hasMoreElements()) {
                            NetworkInterface iface = ifs.nextElement();
                            Enumeration<InetAddress> addrs = iface.getInetAddresses();
                            while(addrs.hasMoreElements()) {
                                InetAddress addr = addrs.nextElement();

                                try {
                                    hostPatterns.add(SubnetInfo.getSubnetInfo(addr));
                                } catch (UnknownHostException e) {
                                    log.warn("could not parse interface address: {}",e.getMessage());
                                }

                            }
                        }
                    } catch(SocketException ex) {
                        log.warn("could not determine local IP addresses, will use 127.0.0.1/24");

                        try {
                            hostPatterns.add(SubnetInfo.getSubnetInfo("127.0.0.1/24")); // IPv4
                            hostPatterns.add(SubnetInfo.getSubnetInfo("::1/128"));      // IPv6
                        } catch (UnknownHostException e) {
                            log.error("could not parse localhost address: {}",e.getMessage());
                        }
                    }
                } else if(host.matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+\\./[0-9]+$")) {
                    // CIDR notation
                    try {
                        hostPatterns.add(SubnetInfo.getSubnetInfo(host));
                    } catch (UnknownHostException e) {
                        log.warn("could not parse host specification '{}': {}",host,e.getMessage());
                    }

                } else if(host.matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$")) {
                    // IP address
                    try {
                        hostPatterns.add(SubnetInfo.getSubnetInfo(host+"/32"));
                    } catch (UnknownHostException e) {
                        log.warn("could not parse host specification '{}': {}",host,e.getMessage());
                    }

                } else if (IPAddressUtil.isIPv6LiteralAddress(host)) {
                    // IPv6 address
                    try {
                        hostPatterns.add(SubnetInfo.getSubnetInfo(host));
                    } catch (UnknownHostException e) {
                        log.warn("could not parse host specification '{}': {}",host,e.getMessage());
                    }


                } else {
                    log.warn("invalid host name specification: {}; please use either CIDR u.v.w.x/zz notation or the keyword LOCAL", host);
                }
            } catch(IllegalArgumentException ex) {
                log.warn("illegal host specification for security constraint {}; not in CIDR notation!",host);
            }
        }
        return hostPatterns;
    }

    /**
     * React to a change in the security configuration, e.g. when the profile is changed.
     * @param event
     */
    public void configurationChangedEvent(@Observes ConfigurationChangedEvent event) {
        if (profileLoading) return;

        boolean load = false, init = false;
        for (String key : event.getKeys()) {
            if ("security.profile".equals(key)) {
                load = true;
            } else if (key.startsWith("security")) {
                init = true;
            }
        }

        if (load) {
            loadSecurityProfile(configurationService.getStringConfiguration("security.profile"));
        }
        if (init) {
            log.info("Access Control Filter reloading. Access control is {}.",configurationService.getBooleanConfiguration("security.enabled",true)?"enabled":"disabled");
            initSecurityConstraints();
        }
    }


    /**
     * Check whether access is granted for the given request. Returns true if the security system grants access.
     * Returns false if access is denied; in this case, the caller may decide to sent an authorization request to
     * the client.
     *
     * @param request
     * @return true in case the active security constraints grant access, false otherwise
     */
    @Override
    public boolean grantAccess(HttpServletRequest request) {
        if(configurationService.getBooleanConfiguration("security.enabled",true)) {
            if(!configurationService.getBooleanConfiguration("security.configured")) {
                loadSecurityProfile(configurationService.getStringConfiguration("security.profile"));
            }


            for(SecurityConstraint constraint : constraints) {
                if(constraint.matches(request)) {
                    if(constraint.getType() == SecurityConstraint.Type.PERMISSION) {
                        log.debug("access to {} granted; {}", request.getRequestURL(), constraint);
                        return true;
                    } else {
                        log.debug("access to {} denied; {}", request.getRequestURL(), constraint);
                        return false;
                    }
                }
            }

            log.debug("access to {} denied; no rule matched",request.getRequestURL());
            return false;
        } else
            return true;
    }


    /**
     * Load a pre-configured security profile from the classpath. When calling this method, the service will
     * look for files called security-profile.<name>.properties and replace all existing security constraints by
     * the new security constraints.
     *
     * @param profile
     */
    @Override
    public void loadSecurityProfile(String profile) {
        profileLoading = true;

        URL securityConfigUrl = this.getClass().getClassLoader().getResource("security-profile."+profile+".properties");

        if(securityConfigUrl != null) {
            Configuration securityConfig = null;
            try {
                securityConfig = new PropertiesConfiguration(securityConfigUrl);

                // remove all configuration keys that define permissions or restrictions
                for(String type : Lists.newArrayList("permission","restriction")) {
                    // determine the names of constraints that are configured
                    for(String key : configurationService.listConfigurationKeys("security."+type)) {
                        configurationService.removeConfiguration(key);
                    }
                }

                for(Iterator<String> keys = securityConfig.getKeys() ; keys.hasNext(); ) {
                    String key = keys.next();

                    configurationService.setConfigurationWithoutEvent(key, securityConfig.getProperty(key));
                }

                configurationService.setConfigurationWithoutEvent("security.configured", true);
            } catch (ConfigurationException e) {
                log.error("error parsing security-profile.{}.properties file at {}: {}",new Object[] {profile,securityConfigUrl,e.getMessage()});
            }

        }

        profileLoading = false;

        initSecurityConstraints();
    }

    @Override
    public List<SecurityConstraint> listSecurityConstraints() {
        return constraints;
    }

    /**
     * Does nothing, just ensures the security service is properly initialised.
     * TODO: this is a workaround and should be fixed differently.
     */
    @Override
    public void ping() {

    }
}
