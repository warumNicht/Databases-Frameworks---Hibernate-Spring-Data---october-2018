package productsshop.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface XmlParser {

    <T> T parseXml(Class<T> objectClass, String filePath) throws JAXBException, FileNotFoundException, UnsupportedEncodingException;

    <T> void exportToXml(T object, String filePath) throws JAXBException;
}
