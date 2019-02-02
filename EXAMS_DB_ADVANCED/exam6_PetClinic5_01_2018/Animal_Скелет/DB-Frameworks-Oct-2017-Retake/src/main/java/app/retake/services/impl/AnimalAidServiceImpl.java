package app.retake.services.impl;


import app.retake.domain.dto.importDtos.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalAidServiceImpl implements AnimalAidService {
    private AnimalAidRepository animalAidRepository;
    private ModelParser mapper;
    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelParser mapper) {
        this.animalAidRepository = animalAidRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = this.mapper.convert(dto, AnimalAid.class);
        this.animalAidRepository.saveAndFlush(animalAid);
    }
}
