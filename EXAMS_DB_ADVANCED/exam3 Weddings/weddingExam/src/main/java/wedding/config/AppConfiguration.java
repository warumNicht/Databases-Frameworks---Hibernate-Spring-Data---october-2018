package wedding.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wedding.util.*;

@Configuration
public class AppConfiguration {
    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setPrettyPrinting().create();
    }
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
    @Bean
    public XmlParser parser(){
        return new XmlParserImpl();
    }
}
