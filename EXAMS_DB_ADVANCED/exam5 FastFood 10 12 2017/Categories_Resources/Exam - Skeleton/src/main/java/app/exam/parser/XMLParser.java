package app.exam.parser;

import app.exam.util.XmlDateAdapter;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements Parser {


    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws JAXBException, FileNotFoundException {
        JAXBContext context=JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller=context.createUnmarshaller();

        StringReader reader=new StringReader(fileContent);

        T res = (T) unmarshaller.unmarshal(reader);
        return res;
    }

    @Override
    public <T> String write(T object) throws JAXBException {
        JAXBContext context=JAXBContext.newInstance(object.getClass());
        Marshaller marshaller=context.createMarshaller();
        XmlDateAdapter adapter =new XmlDateAdapter();
        marshaller.setAdapter(adapter);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();

        marshaller.marshal(object,writer);
        return writer.toString();
    }
}
