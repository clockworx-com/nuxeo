package org.nuxeo.template.serializer.service;


import org.nuxeo.template.serializer.executors.Serializer;

public interface SerializerService {

    Serializer getSerializer(String name);
}
