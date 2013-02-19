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
package org.apache.marmotta.kiwi.model.rdf;

import at.newmedialab.sesame.commons.util.DateUtils;
import org.openrdf.model.Literal;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Add file description here!
 * <p/>
 * Author: Sebastian Schaffert
 */
public class KiWiDateLiteral extends KiWiStringLiteral {

	private static final long serialVersionUID = -7710255732571214481L;


	private Date dateContent;


    private static DatatypeFactory dtf;
    static {
        try {
            dtf = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
        }
    }


    public KiWiDateLiteral() {
        super();
    }


    public KiWiDateLiteral(Date dateContent, KiWiUriResource type) {
        super(DateUtils.getXMLCalendar(DateUtils.getDateWithoutFraction(dateContent)).toXMLFormat(),null,type);
        setDateContent(dateContent);
    }


    public Date getDateContent() {
        return new Date(dateContent.getTime());
    }

    public void setDateContent(Date dateContent) {
        this.dateContent = DateUtils.getDateWithoutFraction(dateContent);
        this.content = DateUtils.getXMLCalendar(this.dateContent).toXMLFormat();
    }

    /**
     * Return the content of the literal, using the parametrized Java type
     *
     * @return
     */
    @Override
    public String getContent() {
        return DateUtils.getXMLCalendar(dateContent).toXMLFormat();
    }

    /**
     * Set the content of the literal to the content provided as parameter.
     *
     * @param content
     */
    @Override
    public void setContent(String content) {
        setDateContent(DateUtils.parseDate(content));
    }


    /**
     * Gets the label of this literal.
     *
     * @return The literal's label.
     */
    @Override
    public String getLabel() {
        return calendarValue().toXMLFormat();
    }

    /**
     * Returns the {@link javax.xml.datatype.XMLGregorianCalendar} value of this literal. A calendar
     * representation can be given for literals whose label conforms to the
     * syntax of the following <a href="http://www.w3.org/TR/xmlschema-2/">XML
     * Schema datatypes</a>: <tt>dateTime</tt>, <tt>time</tt>,
     * <tt>date</tt>, <tt>gYearMonth</tt>, <tt>gMonthDay</tt>,
     * <tt>gYear</tt>, <tt>gMonth</tt> or <tt>gDay</tt>.
     *
     * @return The calendar value of the literal.
     * @throws IllegalArgumentException If the literal cannot be represented by a
     *                                  {@link javax.xml.datatype.XMLGregorianCalendar}.
     */
    @Override
    public XMLGregorianCalendar calendarValue() {
        GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        cal.setTime(getDateContent());

        XMLGregorianCalendar xml_cal = dtf.newXMLGregorianCalendar(cal).normalize();
        xml_cal.setTimezone(0);
        return xml_cal;
    }


    /**
     * A separate equalsContent method for checking whether literals are equal in their content; we cannot override
     * the .equals function with this because it would break the system in cases where the same content is used in
     * different literals.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if(o instanceof Literal) {
            Literal that = (Literal)o;

            if(!(this.getDatatype().equals(that.getDatatype()))) return false;

            return calendarValue().toGregorianCalendar().getTime().getTime() == that.calendarValue().toGregorianCalendar().getTime().getTime();
        }
        return false;
    }


}
