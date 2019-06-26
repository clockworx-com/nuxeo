/*
 * (C) Copyright 2012 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Thierry Delprat
 */
package org.nuxeo.ecm.platform.template.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.nuxeo.template.api.InputType.HTMLValue;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.Blobs;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.impl.blob.FileBlob;
import org.nuxeo.ecm.core.blob.binary.DefaultBinaryManager;
import org.nuxeo.template.api.TemplateInput;
import org.nuxeo.template.api.adapters.TemplateBasedDocument;
import org.nuxeo.template.processors.xdocreport.ZipXmlHelper;

public class TestXDocReportDOCXProcessingWithFormattedTemplateData extends SimpleTemplateDocTestCase {

    private DefaultBinaryManager binaryManager;

    @Before
    public void setUp() throws Exception {
        binaryManager = new DefaultBinaryManager();
        binaryManager.initialize(null, new HashMap<>());

    }

    @Test
    public void whenTemplateDataContainsHTML_italic_shouldGenerateFinalDocWithFormatedData() throws Exception {

        TemplateBasedDocument adapter = setupTestDocs();
        DocumentModel testDoc = adapter.getAdaptedDoc();
        assertNotNull(testDoc);

        List<TemplateInput> params = getHTMLTestParams("Hello <em>world</em> !");

        adapter.saveParams(TEMPLATE_NAME, params, true);
        session.save();

        FileBlob newBlob = (FileBlob) adapter.renderAndStoreAsAttachment(TEMPLATE_NAME, true);

        assertContentEquals("data/expectedResults/italic.docx", newBlob);
    }

    @Test
    public void whenTemplateDataContainsHTML_bold_shouldGenerateFinalDocWithFormatedData() throws Exception {

        TemplateBasedDocument adapter = setupTestDocs();
        DocumentModel testDoc = adapter.getAdaptedDoc();
        assertNotNull(testDoc);

        List<TemplateInput> params = getHTMLTestParams("Hello <strong>world</strong> !");

        adapter.saveParams(TEMPLATE_NAME, params, true);
        session.save();

        Blob newBlob = adapter.renderAndStoreAsAttachment(TEMPLATE_NAME, true);

        assertContentEquals("data/expectedResults/bold.docx", newBlob);
    }

    @Test
    public void whenTemplateDataContainsHTML_CRLF_shouldGenerateFinalDocWithFormatedData() throws Exception {

        TemplateBasedDocument adapter = setupTestDocs();
        DocumentModel testDoc = adapter.getAdaptedDoc();
        assertNotNull(testDoc);

        List<TemplateInput> params = getHTMLTestParams("Hello <br/>world !");

        adapter.saveParams(TEMPLATE_NAME, params, true);
        session.save();

        Blob newBlob = adapter.renderAndStoreAsAttachment(TEMPLATE_NAME, true);

        assertContentEquals("data/expectedResults/CRLF.docx", newBlob);
    }

    @Test
    public void whenTemplateDataContainsHTML_PointList_shouldGenerateFinalDocWithFormatedData() throws Exception {

        TemplateBasedDocument adapter = setupTestDocs();
        DocumentModel testDoc = adapter.getAdaptedDoc();
        assertNotNull(testDoc);

        List<TemplateInput> params = getHTMLTestParams("Hello <ul><li>Benjamin</li><li>Anton</li><li>Sara</li></ul>");

        adapter.saveParams(TEMPLATE_NAME, params, true);
        session.save();

        Blob newBlob = adapter.renderAndStoreAsAttachment(TEMPLATE_NAME, true);

        assertContentEquals("data/expectedResults/point-list.docx", newBlob);
    }

    @Test
    public void whenTemplateDataContainsHTML_NumberList_shouldGenerateFinalDocWithFormatedData() throws Exception {

        TemplateBasedDocument adapter = setupTestDocs();
        DocumentModel testDoc = adapter.getAdaptedDoc();
        assertNotNull(testDoc);

        List<TemplateInput> params = getHTMLTestParams("Hello <ol><li>Benjamin</li><li>Anton</li><li>Sara</li></ol>");

        adapter.saveParams(TEMPLATE_NAME, params, true);
        session.save();

        Blob newBlob = adapter.renderAndStoreAsAttachment(TEMPLATE_NAME, true);
        assertContentEquals("data/expectedResults/number-list.docx", newBlob);
    }

    public void assertContentEquals(String path, Blob blob) throws IOException {
        String actualXmlContent = ZipXmlHelper.readXMLContent(blob, ZipXmlHelper.DOCX_MAIN_FILE);

        File file = org.nuxeo.common.utils.FileUtils.getResourceFileFromContext(path);
        String expectedXmlContent = ZipXmlHelper.readXMLContent(new FileBlob(file), ZipXmlHelper.DOCX_MAIN_FILE);

        assertEquals(expectedXmlContent, actualXmlContent);
    }

    @Override
    protected Blob getTemplateBlob() throws IOException {
        File file = org.nuxeo.common.utils.FileUtils.getResourceFileFromContext("data/testFormatting.docx");
        Blob fileBlob = Blobs.createBlob(file);
        fileBlob.setFilename("testDoc.docx");
        return fileBlob;
    }

    protected List<TemplateInput> getHTMLTestParams(String html) {
        List<TemplateInput> params = new ArrayList<>();
        TemplateInput input = TemplateInput.factory("formatedText", HTMLValue, html);
        params.add(input);
        return params;
    }

    public class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
        }
    }
}
