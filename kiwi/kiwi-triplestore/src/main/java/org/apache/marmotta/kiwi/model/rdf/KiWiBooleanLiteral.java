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

/**
 * Add file description here!
 * <p/>
 * Author: Sebastian Schaffert
 */
public class KiWiBooleanLiteral extends KiWiStringLiteral {

	private static final long serialVersionUID = -1008165335616932374L;

	private boolean value;

    public KiWiBooleanLiteral() {
    }

    public KiWiBooleanLiteral(boolean value, KiWiUriResource type) {
        super();
        setValue(value);
        setType(type);
    }

    public boolean booleanValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value   = value;
        this.content = Boolean.toString(value);
    }
}
