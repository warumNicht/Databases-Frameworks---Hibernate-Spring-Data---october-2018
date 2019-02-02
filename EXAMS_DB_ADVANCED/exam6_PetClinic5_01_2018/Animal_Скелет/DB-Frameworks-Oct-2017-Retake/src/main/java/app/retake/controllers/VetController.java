package app.retake.controllers;

import app.retake.domain.dto.importDtos.vets.VetWrapperXMLImportDTO;
import app.retake.domain.dto.importDtos.vets.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.XMLParser;
import app.retake.services.api.VetService;
import app.retake.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {
    private VetService vetService;
    private XMLParser xmlParser;
@Autowired
    public VetController(VetService vetService, XMLParser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) throws IOException, JAXBException {
        VetWrapperXMLImportDTO vetWrapperXMLImportDTO = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
        StringBuilder res=new StringBuilder();
        for (VetXMLImportDTO vetXMLImportDTO : vetWrapperXMLImportDTO.getVetXMLImportDTOS()) {
            if (!ValidationUtil.isValid(vetXMLImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            this.vetService.create(vetXMLImportDTO);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,vetXMLImportDTO.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
