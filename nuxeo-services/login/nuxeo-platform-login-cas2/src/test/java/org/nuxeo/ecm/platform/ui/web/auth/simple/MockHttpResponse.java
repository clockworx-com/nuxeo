/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 */

package org.nuxeo.ecm.platform.ui.web.auth.simple;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.mock.MockHttpServletResponse;

public class MockHttpResponse extends MockHttpServletResponse {

    protected final Map<String, String> headers = new HashMap<>();

    @Override
    public void setHeader(String key, String value) {
        if (value == null) {
            headers.remove(value);
        } else {
            headers.put(key, value);
        }
    }

}
