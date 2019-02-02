package pb2_carsDealer.config;

import pb2_carsDealer.io.FileIOUtil;
import pb2_carsDealer.io.FileIOUtilImpl;
import pb2_carsDealer.util.ValidatorUtil;
import pb2_carsDealer.util.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pb2_carsDealer.util.XmlParser;
import pb2_carsDealer.util.XmlParserImpl;

@Configuration
public class CarDealerConfiguration {
    @Bean
    public FileIOUtil fileIOUtil() {
        return new FileIOUtilImpl();
    }


    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }
}
