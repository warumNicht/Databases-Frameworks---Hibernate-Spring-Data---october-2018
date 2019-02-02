package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.util.TextUtil;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {
    private ItemsService itemsService;
    private ItemsRepository itemsRepository;
    private JSONParser gson;
    private ValidationUtil validationUtil;

    @Autowired
    public ItemsController(ItemsService itemsService, ItemsRepository itemsRepository, JSONParser gson, ValidationUtil validationUtil) {
        this.itemsService = itemsService;
        this.itemsRepository = itemsRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        ItemJSONImportDTO[] importDTOS = this.gson.read(ItemJSONImportDTO[].class, jsonContent);
        StringBuilder res = new StringBuilder();
        for (ItemJSONImportDTO importDTO : importDTOS) {
            if (!this.validationUtil.isValid(importDTO)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(this.itemsRepository.existsByName(importDTO.getName())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            this.itemsService.create(importDTO);

            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,importDTO.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
