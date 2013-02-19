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
package kiwi.core.api.io;

import org.openrdf.rio.RDFWriter;

/**
 * User: Thomas Kurz
 * Date: 08.08.12
 * Time: 11:02
 */
public interface RDFHtmlWriter extends RDFWriter {

	  public RDFWriterPriority getPriority();

}
