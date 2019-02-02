package app.exam.config;

import app.exam.io.interfaces.FileIO;
import app.exam.io.interfaces.FileUtilImpl;
import app.exam.parser.JSONParser;
import app.exam.parser.ModelParserImpl;
import app.exam.parser.ValidationUtilImpl;
import app.exam.parser.interfaces.ModelParser;
import app.exam.parser.interfaces.ValidationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    public static final String EMPLOYEES_IMPORT_JSON = "/files/json/employees.json";

    public static final String ITEMS_IMPORT_JSON = "/files/json/items.json";

    public static final String ORDERS_IMPORT_XML = "/files/xml/orders.xml";
    @Bean
    public ModelParser modelParser(){
        return new ModelParserImpl();
    }
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
//    @Bean
//    public JSONParser jsonParser(){
//        return new JSONParser();
//    }
    @Bean
    public FileIO fileIO(){
        return new FileUtilImpl();
    }
}
