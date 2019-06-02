package org.nuxeo.template.serializer.executors;

import org.dom4j.DocumentException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.template.api.TemplateInput;

import java.util.List;

/**
 * (was XMLSerializer)
 * {@link TemplateInput} parameters are stored in the {@link DocumentModel} as a single String Property via XML
 * Serialization. This class contains the Serialization/Deserialization logic.
 *
 * @author Tiry (tdelprat@nuxeo.com)
 * @author bjalon (bjalon@qastia.com)
 */
public interface Serializer {

    List<TemplateInput> doDeserialization(String content) throws DocumentException;

    String doSerialization(List<TemplateInput> content);
}
