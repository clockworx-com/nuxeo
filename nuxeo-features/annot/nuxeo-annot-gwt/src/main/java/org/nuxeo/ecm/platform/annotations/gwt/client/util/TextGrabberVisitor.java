/*
 * (C) Copyright 2006-2008 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Alexandre Russel
 *
 * $Id$
 */

package org.nuxeo.ecm.platform.annotations.gwt.client.util;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Text;

/**
 * @author Alexandre Russel
 */
public class TextGrabberVisitor implements NodeProcessor {
    private StringBuilder builder = new StringBuilder();

    @Override
    public boolean doBreak() {
        return false;
    }

    @Override
    public void process(Node node) {
        if (node.getNodeType() == Node.TEXT_NODE) {
            Text text = (Text) node;
            builder.append(text.getNodeValue());
        }
    }

    public String getText() {
        Log.debug("offset text: " + builder.toString());
        return builder.toString();
    }
}
