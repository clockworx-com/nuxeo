package org.nuxeo.template.serializer.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.runtime.model.Descriptor;
import org.nuxeo.template.serializer.executors.Serializer;

@XObject("serializer")
public class SerializerContribution implements Descriptor {

    @XNode("@class")
    public Class<Serializer> implementationClass;

    @XNode("@name")
    public String name;

    @Override
    public String getId() {
        return StringUtils.defaultIfBlank(name, "default");
    }

    public Serializer getImplementation() {
        Serializer obj;
        try {
            obj = implementationClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new NuxeoException("Serializer Contribution NoDefaultConstructorDefined: " + name);
        } catch (IllegalAccessException e) {
            throw new NuxeoException("Serializer Contribution DefaultConstructorHidden: " + name);
        } catch (InstantiationException | InvocationTargetException e) {
            throw new NuxeoException("Serializer Contribution ErrorOnInstanciation: " + name);
        }
        return obj;
    }
}
