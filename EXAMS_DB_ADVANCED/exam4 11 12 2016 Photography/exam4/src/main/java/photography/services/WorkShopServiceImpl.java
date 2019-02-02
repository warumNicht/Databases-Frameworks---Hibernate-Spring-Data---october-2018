package photography.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.domain.dto.importDto.workshops.ParticipantDto;
import photography.domain.dto.importDto.workshops.WorkShopImportDto;
import photography.domain.dto.importDto.workshops.WorkshopRootDto;
import photography.domain.dto.query4.*;
import photography.domain.entities.Location;
import photography.domain.entities.Photographer;
import photography.domain.entities.Workshop;
import photography.repositories.LocationRepo;
import photography.repositories.PhotographerRepo;
import photography.repositories.WorkSopRepo;
import photography.services.interfaces.WorkShopService;
import photography.util.TextUtil;
import photography.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class WorkShopServiceImpl implements WorkShopService {
    private WorkSopRepo workSopRepo;
    private PhotographerRepo photographerRepo;
    private LocationRepo locationRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public WorkShopServiceImpl(WorkSopRepo workSopRepo, PhotographerRepo photographerRepo,
                               LocationRepo locationRepo, ModelMapper mapper, ValidationUtil validationUtil) {
        this.workSopRepo = workSopRepo;
        this.photographerRepo = photographerRepo;
        this.locationRepo = locationRepo;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importWorkshops(WorkshopRootDto workshopRootDto) {
        StringBuilder res = new StringBuilder();
        for (WorkShopImportDto workImportDto : workshopRootDto.getWorkShopImportDtos()) {
            if (!this.validationUtil.isValid(workImportDto)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Workshop workshop = this.mapper.map(workImportDto, Workshop.class);
            workshop.setParticipants(new LinkedHashSet<>());
            if (!this.locationRepo.existsByName(workImportDto.getLocation())) {
                Location location = new Location();
                location.setName(workImportDto.getLocation());
                this.locationRepo.saveAndFlush(location);
            }
            Location location = this.locationRepo.getByName(workImportDto.getLocation()).orElse(null);
            if (location == null) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            workshop.setLocation(location);
            List<Photographer> train = this.photographerRepo.getByFullName(workImportDto.getTrainer());
            if (train.size() != 1) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Photographer trainer = train.get(0);
            workshop.setTrainer(trainer);
            if (workImportDto.getParticipantDtos() != null)
                for (ParticipantDto participantDto : workImportDto.getParticipantDtos()) {
                    List<Photographer> photos = this.photographerRepo
                            .findAllByFirstNameAndLastName(participantDto.getFirstName(), participantDto.getLastName());
                    if (photos.size() == 1) {
                        Photographer photographer = photos.get(0);
                        workshop.getParticipants().add(photographer);
                    }
                }
            this.workSopRepo.saveAndFlush(workshop);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,
                    workshop.getName())).append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public LocRootExportDto query4() {
        List<Location> locations = this.locationRepo.allWithWorkshopsWith5Participants();

        LocRootExportDto res = new LocRootExportDto();
        List<LocationWorkExportDto> locationWorkExportDtos = new ArrayList<>();

        for (Location location : locations) {
            LocationWorkExportDto locationWorkExportDto = this.createLocationDto(location);
            locationWorkExportDtos.add(locationWorkExportDto);
        }
        res.setLocRootExportDtos(locationWorkExportDtos);
        return res;
    }

    private LocationWorkExportDto createLocationDto(Location location) {
        LocationWorkExportDto res = new LocationWorkExportDto();
        res.setName(location.getName());

        List<WorkShopLocExportDto> workShopLocExportDtos = new ArrayList<>();
        for (Workshop workshop : location.getWorkshops()) {
            if (workshop.getParticipants().size() < 5) {
                continue;
            }
            WorkShopLocExportDto workShopLocExportDto =this.createWorkshop(workshop);
            workShopLocExportDtos.add(workShopLocExportDto);
        }
        res.setWorkShopLocExportDtos(workShopLocExportDtos);
        return res;
    }

    private WorkShopLocExportDto createWorkshop(Workshop workshop) {
        WorkShopLocExportDto res = new WorkShopLocExportDto();
        res.setName(workshop.getName());
        String totalProfit = this.calculateTotalProfit(workshop);
        res.setTotalProfit(totalProfit);
        ParticipantCountExportDto participantCountExportDto =this.createParticipantCount(workshop);
        res.setParticipantCountExportDto(participantCountExportDto);
        return res;
    }

    private ParticipantCountExportDto createParticipantCount(Workshop workshop) {
        ParticipantCountExportDto res = new ParticipantCountExportDto();
        res.setCount(workshop.getParticipants().size());
        List<ParticipantExportDto> participants=new ArrayList<>();
        for (Photographer photographer : workshop.getParticipants()) {
            ParticipantExportDto participantExportDto = new ParticipantExportDto();
            participantExportDto.setName(String.format("%s %s", photographer.getFirstName(),
                    photographer.getLastName()));
            participants.add(participantExportDto);
        }
        res.setParticipantExportDtos(participants);
        return res;
    }

    private String calculateTotalProfit(Workshop workshop) {
        BigDecimal res = BigDecimal.valueOf(workshop.getParticipants().size());
        res = res.multiply(workshop.getPrice());
        res=res.multiply(BigDecimal.valueOf(0.8));
        return String.format("%.3f", res);
    }
}
