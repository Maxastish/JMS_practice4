package com.example.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class XmlUtil {

    public static String toXml(Object object, String xslPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"" + xslPath + "\"?>\n");

            marshaller.marshal(object, writer);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("XML serialization error", e);
        }
    }
}
