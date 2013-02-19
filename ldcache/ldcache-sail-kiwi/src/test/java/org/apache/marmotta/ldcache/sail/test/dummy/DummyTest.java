/*
 * Copyright (c) 2013 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.marmotta.ldcache.sail.test.dummy;

import org.apache.marmotta.ldclient.api.ldclient.LDClientService;
import org.apache.marmotta.ldclient.model.ClientConfiguration;
import org.apache.marmotta.ldclient.model.ClientResponse;
import org.apache.marmotta.ldclient.services.ldclient.LDClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.repository.RepositoryConnection;

/**
 * Test if the dummy components work.
 * <p/>
 * Author: Sebastian Schaffert (sschaffert@apache.org)
 */
public class DummyTest {

    private LDClientService ldclient;


    @Before
    public void setupClient() {
        ClientConfiguration config = new ClientConfiguration();
        config.addEndpoint(new DummyEndpoint());

        ldclient = new LDClient(config);
    }

    @After
    public void shutdownClient() {
        ldclient.shutdown();
    }

    @Test
    public void testDummyProvider() throws Exception {
        ClientResponse resp1 = ldclient.retrieveResource("http://remote/resource1");
        RepositoryConnection con1 = resp1.getTriples().getConnection();
        try {
            con1.begin();
            Assert.assertEquals(3, con1.size());
            con1.commit();
        } finally {
            con1.close();
        }

        ClientResponse resp2 = ldclient.retrieveResource("http://remote/resource2");
        RepositoryConnection con2 = resp2.getTriples().getConnection();
        try {
            con2.begin();
            Assert.assertEquals(2, con2.size());
            con2.commit();
        } finally {
            con2.close();
        }

        ClientResponse resp3 = ldclient.retrieveResource("http://remote/resource3");
        RepositoryConnection con3 = resp3.getTriples().getConnection();
        try {
            con3.begin();
            Assert.assertEquals(2, con3.size());
            con3.commit();
        } finally {
            con3.close();
        }
    }

}
