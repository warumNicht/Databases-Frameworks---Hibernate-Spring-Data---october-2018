package app.retake.controllers;

import app.retake.domain.dto.importDtos.AnimalAidJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.IOException;
@Controller
public class AnimalAidController {
    private AnimalAidService animalAidService;
    private AnimalAidRepository animalAidRepository;
    private JSONParser jsonParser;
    private ValidationUtil validationUtil;
    private ModelParser mapper;
@Autowired
    public AnimalAidController(AnimalAidService animalAidService, AnimalAidRepository animalAidRepository, JSONParser jsonParser, ValidationUtil validationUtil, ModelParser mapper) {
        this.animalAidService = animalAidService;
    this.animalAidRepository = animalAidRepository;
    this.jsonParser = jsonParser;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
}

    public String importDataFromJSON(String jsonContent) throws IOException {
        AnimalAidJSONImportDTO[] animalAidJSONImportDTOS = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
        StringBuilder res=new StringBuilder();
        for (AnimalAidJSONImportDTO animalAidJSONImportDTO : animalAidJSONImportDTOS) {
            if (!ValidationUtil.isValid(animalAidJSONImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(this.animalAidRepository
                    .existsByNameAndPrice(animalAidJSONImportDTO.getName(),animalAidJSONImportDTO.getPrice())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
           this.animalAidService.create(animalAidJSONImportDTO);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,animalAidJSONImportDTO.getName()))
            .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
