package org.softuni.mostwanted.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.io.impl.FileIOImpl;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.parser.interfaces.ModelParserImpl;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.parser.interfaces.ParserFileImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }
    @Bean
    public ModelParser mapper(){
        return new ModelParserImpl();
    }
    @Bean
    public FileIO fileUtil(){
        return new FileIOImpl();
    }
    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtil();
    }
    @Bean
    public Parser parser(){
        return  new ParserFileImpl();
    }
}
