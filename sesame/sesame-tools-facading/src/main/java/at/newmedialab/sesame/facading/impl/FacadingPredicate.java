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
package at.newmedialab.sesame.facading.impl;

/**
 * Simple class encapsulating the predicate/property uris.
 */
public class FacadingPredicate {

    private final boolean inverse;
    private final String[] properties;

    public FacadingPredicate(boolean inverse, String... property) {
        this.inverse = inverse;
        this.properties = property;
    }

    public FacadingPredicate(String... property) {
        this(false, property);
    }

    public boolean isInverse() {
        return inverse;
    }

    public String[] getProperties() {
        return properties;
    }

}
