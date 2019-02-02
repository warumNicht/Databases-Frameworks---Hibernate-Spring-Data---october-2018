package app.retake.controllers;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.dto.importDtos.animalsPassports.AnimalJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import app.retake.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class AnimalController {
    private AnimalService animalService;
    private PassportRepository passportRepository;
    private JSONParser jsonParser;
    private ValidationUtil validationUtil;
@Autowired
    public AnimalController(AnimalService animalService, PassportRepository passportRepository, JSONParser jsonParser, ValidationUtil validationUtil) {
        this.animalService = animalService;
    this.passportRepository = passportRepository;
    this.jsonParser = jsonParser;
        this.validationUtil = validationUtil;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, ParseException {
        AnimalJSONImportDTO[] animalJSONImportDTOS = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);

        StringBuilder res=new StringBuilder();

        for (AnimalJSONImportDTO animalJSONImportDTO : animalJSONImportDTOS) {
            if (!ValidationUtil.isValid(animalJSONImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!ValidationUtil.isValid(animalJSONImportDTO.getPassport())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if(this.passportRepository.existsBySerialNumber(animalJSONImportDTO.getPassport().getSerialNumber())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            this.animalService.create(animalJSONImportDTO);
            res.append(String.format("Record %s Passport №: %s successfully imported.",
                    animalJSONImportDTO.getName(),animalJSONImportDTO.getPassport().getSerialNumber()
                    ))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) throws IOException {
        List<AnimalsJSONExportDTO> byOwnerPhoneNumber = this.animalService.findByOwnerPhoneNumber(phoneNumber);
        String jsonContent = this.jsonParser.write(byOwnerPhoneNumber);
        return jsonContent;
    }
}
