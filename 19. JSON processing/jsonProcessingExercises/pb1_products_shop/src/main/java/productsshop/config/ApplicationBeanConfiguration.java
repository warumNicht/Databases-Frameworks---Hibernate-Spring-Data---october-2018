package productsshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productsshop.io.FileIOUtil;
import productsshop.io.FileIOUtilImpl;
import productsshop.util.ValidatorUtil;
import productsshop.util.ValidatorUtilImpl;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public FileIOUtil fileIOUtil(){
        return new FileIOUtilImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }
    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
