package app.retake.controllers;

import app.retake.domain.dto.query2.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureAnimalAidXMLImportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.XMLParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.VetRepository;
import app.retake.services.api.ProcedureService;
import app.retake.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {
    private ProcedureService procedureService;
    private VetRepository vetRepository;
    private AnimalRepository animalRepository;
    private AnimalAidRepository animalAidRepository;
    private XMLParser xmlParser;
@Autowired
    public ProcedureController(ProcedureService procedureService, VetRepository vetRepository,
                               AnimalRepository animalRepository,
                               AnimalAidRepository animalAidRepository, XMLParser xmlParser) {
        this.procedureService = procedureService;
        this.vetRepository = vetRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) throws IOException, JAXBException, ParseException {

        ProcedureWrapperXMLImportDTO procedureWrapperXMLImportDTO = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        StringBuilder res=new StringBuilder();

        for (ProcedureXMLImportDTO procedureXMLImportDTO : procedureWrapperXMLImportDTO.getProcedureXMLImportDTOS()) {

            if (!ValidationUtil.isValid(procedureXMLImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if (!this.vetRepository.existsByName(procedureXMLImportDTO.getVet())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.animalRepository.existsByPassportSerialNumber(procedureXMLImportDTO.getAnimal())){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.chechAids(procedureXMLImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.procedureService.create(procedureXMLImportDTO);

            res.append(TextUtil.SUCCESS_MESSAGE_SIMPLE)
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    private boolean chechAids(ProcedureXMLImportDTO procedureXMLImportDTO) {
        boolean res=true;
        for (ProcedureAnimalAidXMLImportDTO procedureAnimalAidXMLImportDTO : procedureXMLImportDTO.getAnimalCollectionDto().getProcedureAnimalAidXMLImportDTOS()) {
            if(!this.animalAidRepository
                    .existsByName(procedureAnimalAidXMLImportDTO.getName())){
                res=false;
                break;
            }
        }
        return res;
    }

    public String exportProcedures() throws IOException, JAXBException {
        ProcedureWrapperXMLExportDTO procedureWrapperXMLExportDTO = this.procedureService.exportProcedures();

        String resToFile = this.xmlParser.write(procedureWrapperXMLExportDTO);

        return resToFile;
    }
}
