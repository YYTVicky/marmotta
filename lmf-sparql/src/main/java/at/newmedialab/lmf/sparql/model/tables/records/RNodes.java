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
/**
 * This class is generated by jOOQ
 */
package at.newmedialab.lmf.sparql.model.tables.records;

import at.newmedialab.lmf.sparql.model.tables.TNodes;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.2.0"},
                            comments = "This class is generated by jOOQ")
public class RNodes extends org.jooq.impl.TableRecordImpl<RNodes> {

	private static final long serialVersionUID = 1708898505;

	/**
	 * An uncommented item
	 */
	public void setNodetype(java.lang.String value) {
		setValue(TNodes.KIWINODE.NODETYPE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getNodetype() {
		return getValue(TNodes.KIWINODE.NODETYPE);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setId(java.lang.Long value) {
		setValue(TNodes.KIWINODE.ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.lang.Long getId() {
		return getValue(TNodes.KIWINODE.ID);
	}

	/**
	 * An uncommented item
	 */
	public void setCreated(java.sql.Timestamp value) {
		setValue(TNodes.KIWINODE.CREATED, value);
	}

	/**
	 * An uncommented item
	 */
	public java.sql.Timestamp getCreated() {
		return getValue(TNodes.KIWINODE.CREATED);
	}

	/**
	 * An uncommented item
	 */
	public void setDeleted(java.lang.Boolean value) {
		setValue(TNodes.KIWINODE.DELETED, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Boolean getDeleted() {
		return getValue(TNodes.KIWINODE.DELETED);
	}

	/**
	 * An uncommented item
	 */
	public void setVersion(java.lang.Long value) {
		setValue(TNodes.KIWINODE.VERSION, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Long getVersion() {
		return getValue(TNodes.KIWINODE.VERSION);
	}

	/**
	 * An uncommented item
	 */
	public void setUri(java.lang.String value) {
		setValue(TNodes.KIWINODE.URI, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getUri() {
		return getValue(TNodes.KIWINODE.URI);
	}

	/**
	 * An uncommented item
	 */
	public void setAnonid(java.lang.String value) {
		setValue(TNodes.KIWINODE.ANONID, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getAnonid() {
		return getValue(TNodes.KIWINODE.ANONID);
	}

	/**
	 * An uncommented item
	 */
	public void setContentmd5(java.lang.String value) {
		setValue(TNodes.KIWINODE.CONTENTMD5, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getContentmd5() {
		return getValue(TNodes.KIWINODE.CONTENTMD5);
	}

	/**
	 * An uncommented item
	 */
	public void setLanguage(java.lang.String value) {
		setValue(TNodes.KIWINODE.LANGUAGE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getLanguage() {
		return getValue(TNodes.KIWINODE.LANGUAGE);
	}

	/**
	 * An uncommented item
	 */
	public void setType(java.lang.String value) {
		setValue(TNodes.KIWINODE.TYPE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getType() {
		return getValue(TNodes.KIWINODE.TYPE);
	}

	/**
	 * An uncommented item
	 */
	public void setContent(java.lang.String value) {
		setValue(TNodes.KIWINODE.CONTENT, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getContent() {
		return getValue(TNodes.KIWINODE.CONTENT);
	}

	/**
	 * An uncommented item
	 */
	public void setIntcontent(java.lang.Long value) {
		setValue(TNodes.KIWINODE.INTCONTENT, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Long getIntcontent() {
		return getValue(TNodes.KIWINODE.INTCONTENT);
	}

	/**
	 * An uncommented item
	 */
	public void setDoublecontent(java.lang.Double value) {
		setValue(TNodes.KIWINODE.DOUBLECONTENT, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Double getDoublecontent() {
		return getValue(TNodes.KIWINODE.DOUBLECONTENT);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71252fbdfe5
	 * FOREIGN KEY (context_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public void setContextId(java.lang.Long value) {
		setValue(TNodes.KIWINODE.CONTEXT_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71252fbdfe5
	 * FOREIGN KEY (context_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public java.lang.Long getContextId() {
		return getValue(TNodes.KIWINODE.CONTEXT_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71236cf857c
	 * FOREIGN KEY (creator_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public void setCreatorId(java.lang.Long value) {
		setValue(TNodes.KIWINODE.CREATOR_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71236cf857c
	 * FOREIGN KEY (creator_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public java.lang.Long getCreatorId() {
		return getValue(TNodes.KIWINODE.CREATOR_ID);
	}

	/**
	 * Create a detached KiwinodeRecord
	 */
	public RNodes() {
		super(TNodes.KIWINODE);
	}
}
