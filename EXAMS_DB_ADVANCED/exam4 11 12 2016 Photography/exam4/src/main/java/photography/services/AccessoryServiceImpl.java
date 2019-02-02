package photography.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.domain.dto.importDto.LensImportDto;
import photography.domain.dto.importDto.accsessories.AccessoryImportDto;
import photography.domain.dto.importDto.accsessories.AccessoryRootImportDto;
import photography.domain.entities.Accessory;
import photography.domain.entities.Lens;
import photography.domain.entities.Photographer;
import photography.repositories.AccessoryRepo;
import photography.repositories.PhotographerRepo;
import photography.services.interfaces.AccessoryService;
import photography.util.TextUtil;
import photography.util.ValidationUtil;

import java.util.Random;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private AccessoryRepo accessoryRepo;
    private PhotographerRepo photographerRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepo accessoryRepo, PhotographerRepo photographerRepo,
                                ModelMapper mapper, ValidationUtil validationUtil) {
        this.accessoryRepo = accessoryRepo;
        this.photographerRepo = photographerRepo;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importAccessories(AccessoryRootImportDto accessoryRootImportDto) {
        StringBuilder res = new StringBuilder();
        for (AccessoryImportDto accsImportDto : accessoryRootImportDto.getAccessoryImportDtos()) {

            Accessory accessory = this.mapper.map(accsImportDto, Accessory.class);
            Photographer photographer = this.getRandomPfoto();
            accessory.setOwner(photographer);
            this.accessoryRepo.saveAndFlush(accessory);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,
                    accessory.getName())).append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    private Photographer getRandomPfoto() {
        long count = this.photographerRepo.count();
        Random random = new Random();
        int nextInt = random.nextInt((int) count) + 1;
        Photographer photographer = this.photographerRepo.findById(nextInt).orElse(null);
        return photographer;
    }
}
