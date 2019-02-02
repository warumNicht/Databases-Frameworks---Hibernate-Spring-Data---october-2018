package app.retake;

import app.retake.io.FIleIOImpl;
import app.retake.io.api.FileIO;
import app.retake.parser.JSONParser;
import app.retake.parser.ModelParserImpl;
import app.retake.parser.XMLParser;
import app.retake.parser.interfaces.ModelParser;
import app.retake.parser.interfaces.Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class Config {

    @Bean
    public Parser jsonParser(){
        return  new JSONParser();
    }
    @Bean
    public ModelParser modelParser(){
        return new ModelParserImpl();
    }
    @Bean
    public XMLParser  xmlParser(){
        return new XMLParser();
    }
    @Bean
    public FileIO fileIO(){
        return new FIleIOImpl();
    }
}
