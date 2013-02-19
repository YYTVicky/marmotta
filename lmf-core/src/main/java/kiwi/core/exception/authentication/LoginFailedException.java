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
package kiwi.core.exception.authentication;

/**
 * @author sstroka
 *         User: sstroka
 *         Date: 29.09.2011
 *         Time: 17:16:08
 */
public class LoginFailedException extends Exception {

    private static final long serialVersionUID = 672088108405801956L;

    public LoginFailedException(String s) {
        super(s);
    }

}
