package photography.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlParserImpl implements XmlParser {

    @Override
    public <T> void write(T object, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File(filePath);
        marshaller.marshal(object, file);
    }

    @Override
    public <T> T read(Class<T> objectClass, String filePath) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(objectClass);
        InputStreamReader stream = new InputStreamReader(new FileInputStream(new File(filePath)));
        BufferedReader reader = new BufferedReader(stream);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        T result = (T) unmarshaller.unmarshal(reader);
        return result;
    }
}
