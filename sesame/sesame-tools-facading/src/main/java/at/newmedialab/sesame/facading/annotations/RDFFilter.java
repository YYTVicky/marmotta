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
package at.newmedialab.sesame.facading.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a filter for facades that selects the content items that may be facaded based on the
 * type of the content item. Only of a content item satisfies all types specified here is it accepted in
 * a result set.
 * 
 * @author Sebastian Schaffert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE } )
public @interface RDFFilter {
	/**
	 * The URI of the RDF type to use for the class
	 * @return
	 */
	String[] value();

}
