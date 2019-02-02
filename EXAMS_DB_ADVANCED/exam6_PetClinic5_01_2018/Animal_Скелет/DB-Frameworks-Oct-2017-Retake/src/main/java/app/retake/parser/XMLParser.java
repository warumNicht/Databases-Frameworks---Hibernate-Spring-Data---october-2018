package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import app.retake.util.XmlDateAdapter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        JAXBContext context=JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        StringReader reader=new StringReader(fileContent);

        T result = (T) unmarshaller.unmarshal(reader);
        return result;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        JAXBContext context=JAXBContext.newInstance(object.getClass());

        Marshaller marshaller=context.createMarshaller();
        XmlDateAdapter adapter=new XmlDateAdapter();
        marshaller.setAdapter(adapter);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        StringWriter writer=new StringWriter();

        marshaller.marshal(object,writer);
        return writer.toString();
    }
}
