package org.softuni.mostwanted.parser.interfaces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ParserFileImpl implements Parser {
    public ParserFileImpl() {
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext context=JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        StringReader reader = new StringReader(fileContent);
        T unmarshal = (T)unmarshaller.unmarshal(reader);

        return unmarshal;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        JAXBContext context=JAXBContext.newInstance(object.getClass());
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        StringWriter writer=new StringWriter();
        marshaller.marshal(object,writer);
        return writer.toString();
    }
}