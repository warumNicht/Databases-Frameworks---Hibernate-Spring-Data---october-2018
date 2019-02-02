package judge.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import judge.util.FileUtil;
import judge.util.FileUtilImpl;
import judge.util.ValidationUtil;
import judge.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
