package wedding.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {
    <T> void write(T object, String filePath) throws JAXBException;

    <T> T read(Class<T> objectClass, String filePath) throws JAXBException, FileNotFoundException;
}
