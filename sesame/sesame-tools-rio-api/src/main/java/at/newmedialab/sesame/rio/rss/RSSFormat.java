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
package at.newmedialab.sesame.rio.rss;

import org.openrdf.rio.RDFFormat;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Add file description here!
 * <p/>
 * Author: Sebastian Schaffert
 */
public class RSSFormat {


    public static final RDFFormat FORMAT = new RDFFormat(
            "RSS",
            Arrays.asList("application/rss+xml", "application/x-georss+xml"),
            Charset.forName("UTF-8"),
            Arrays.asList("rss"),
            false,
            false
    );
    static {
        RDFFormat.register(FORMAT);
    }


}
