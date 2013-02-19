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
package org.apache.marmotta.ldclient.provider.ldap.mapping;

import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;

import java.util.Collections;
import java.util.Set;

public class LiteralPredicateFactory extends PredicateObjectFactory {

    public LiteralPredicateFactory(String predicateURI) {
        super(predicateURI);
    }

    @Override
    public Set<Value> createObjects(String value, ValueFactory valueFactory) {
        return Collections.singleton((Value) valueFactory.createLiteral(value));
    }

}
