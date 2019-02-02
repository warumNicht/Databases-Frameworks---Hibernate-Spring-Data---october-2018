package cars.config;

import cars.io.FileIOUtil;
import cars.io.FileIOUtilImpl;
import cars.util.ValidatorUtil;
import cars.util.ValidatorUtilImpl;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarDealerConfiguration {
    @Bean
    public FileIOUtil fileIOUtil() {
        return new FileIOUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'H:mm:ss")
                .create(); //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")

//              .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//            @Override
//            public LocalDateTime deserialize(JsonElement jsonElement, Type type,
//                                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                String stringDateJson = jsonElement.getAsString();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                try {
//                    LocalDateTime date = LocalDateTime.parse(stringDateJson, formatter);
//                    return date;
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//
//        })
//                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//                    @Override
//                    public LocalDateTime deserialize(JsonElement jsonElement, Type type,
//                                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//                        return null;
//                    }
//                })
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
