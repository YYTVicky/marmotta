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
package at.newmedialab.sesame.facading.model;

import at.newmedialab.sesame.facading.api.FacadingPredicateBuilder;
import at.newmedialab.sesame.facading.impl.FacadingPredicate;

import java.lang.reflect.Method;

public abstract class AbstractNamespacePropBuilder implements FacadingPredicateBuilder {

    @Override
    public FacadingPredicate getFacadingPredicate(String fieldName, Class<? extends Facade> facade, Method method) {
        return new FacadingPredicate(false, getNamespace() + fieldName);
    }

    protected abstract String getNamespace();

}
