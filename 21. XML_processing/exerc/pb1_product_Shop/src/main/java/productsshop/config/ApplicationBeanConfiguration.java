package productsshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productsshop.util.ValidatorUtil;
import productsshop.util.ValidatorUtilImpl;
import productsshop.util.XmlParser;
import productsshop.util.XmlParserImpl;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
}
