package app.retake.services.impl;

import app.retake.domain.dto.query2.ProcedureAnimalAidXMLExportDTO;
import app.retake.domain.dto.query2.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureAnimalAidXMLImportDTO;
import app.retake.domain.dto.importDtos.procedures.ProcedureXMLImportDTO;
import app.retake.domain.dto.query2.ProcedureXMLExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.ProcedureRepository;
import app.retake.repositories.VetRepository;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {
    private ProcedureRepository procedureRepository;
    private VetRepository vetRepository;
    private AnimalRepository animalRepository;
    private AnimalAidRepository animalAidRepository;
    private ModelParser mapper;
@Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, VetRepository vetRepository,
                                AnimalRepository animalRepository, AnimalAidRepository animalAidRepository,
                                ModelParser mapper) {
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Procedure procedure = this.mapper.convert(dto, Procedure.class);
        procedure.setServices(new LinkedHashSet<>());

        Animal animal = this.animalRepository.findByPassportSerialNumber(dto.getAnimal());
        procedure.setAnimal(animal);
        Vet vet = this.vetRepository.findByName(dto.getVet());
        procedure.setVet(vet);

        for (ProcedureAnimalAidXMLImportDTO procedureAnimalAidXMLImportDTO : dto.getAnimalCollectionDto().getProcedureAnimalAidXMLImportDTOS()) {
            List<AnimalAid> byName = this.animalAidRepository.getByName(procedureAnimalAidXMLImportDTO.getName());
            AnimalAid aid = byName.get(0);
            procedure.getServices().add(aid);
        }
      this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {

        List<Procedure> procedures = this.procedureRepository.findAll();
        ProcedureWrapperXMLExportDTO res=new ProcedureWrapperXMLExportDTO();
        List<ProcedureXMLExportDTO> proceduresRes=new ArrayList<>();
        for (Procedure procedure : procedures) {
            ProcedureXMLExportDTO current=new ProcedureXMLExportDTO();
            current.setAnimal(procedure.getAnimal().getPassport().getSerialNumber());
            current.setOwner(procedure.getAnimal()
                    .getPassport().getOwnerPhoneNumber());
            List<ProcedureAnimalAidXMLExportDTO> aids=new ArrayList<>();

            List<AnimalAid> collect = procedure.getServices().stream().sorted((x, y) -> {
                return y.getPrice().compareTo(x.getPrice());
            }).collect(Collectors.toList());

            for (AnimalAid animalAid : collect) {
                ProcedureAnimalAidXMLExportDTO curAid=new ProcedureAnimalAidXMLExportDTO();
                curAid.setName(animalAid.getName());

                DecimalFormat df = new DecimalFormat("#.##");
                String formatted = df.format(animalAid.getPrice());
                if(!formatted.contains(".")){
                    formatted=formatted+".0";
                }
                curAid.setPrice(formatted);
                aids.add(curAid);
            }
            current.setProcedureAnimalAidXMLExportDTOS(aids);
            proceduresRes.add(current);
        }
        res.setProcedureXMLExportDTOS(proceduresRes);
        return res;
    }
}

