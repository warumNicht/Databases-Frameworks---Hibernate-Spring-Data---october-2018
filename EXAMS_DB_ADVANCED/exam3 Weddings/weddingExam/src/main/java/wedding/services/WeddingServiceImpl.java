package wedding.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wedding.domain.dto.importDto.weddingImport.GuestDto;
import wedding.domain.dto.importDto.weddingImport.WeddingImportDto;
import wedding.domain.dto.query2.AgencyGuestDto;
import wedding.domain.dto.query2.GuestExportDto;
import wedding.domain.dto.query2.WeddingExportDto;
import wedding.domain.entities.*;
import wedding.repositories.AgencyRepository;
import wedding.repositories.PersonRepository;
import wedding.repositories.VenueRepo;
import wedding.repositories.WeddingRepository;
import wedding.services.interfaces.WeddingService;
import wedding.util.TextUtil;
import wedding.util.ValidationUtil;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class WeddingServiceImpl implements WeddingService {
    private WeddingRepository weddingRepository;
    private PersonRepository personRepository;
    private AgencyRepository agencyRepository;
    private VenueRepo venueRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;
@Autowired
    public WeddingServiceImpl(WeddingRepository weddingRepository, PersonRepository personRepository,
                              AgencyRepository agencyRepository, VenueRepo venueRepo, ModelMapper mapper, ValidationUtil validationUtil) {
        this.weddingRepository = weddingRepository;
        this.personRepository = personRepository;
        this.agencyRepository = agencyRepository;
    this.venueRepo = venueRepo;
    this.mapper = mapper;
        this.validationUtil = validationUtil;
    }
    @Override
    public String importWeddings(WeddingImportDto[] weddingImportDtos){
        StringBuilder res=new StringBuilder();
        for (WeddingImportDto weddingImportDto : weddingImportDtos) {
            if(!this.validationUtil.isValid(weddingImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            List<Agency> agencies = this.agencyRepository.findByName(weddingImportDto.getAgency());
            if(agencies.size()!=1){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Person bride = this.personRepository.findByFullName(weddingImportDto.getBride()).orElse(null);
            if(bride==null){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Person bridegroom = this.personRepository.findByFullName(weddingImportDto.getBridegroom()).orElse(null);
            if(bridegroom==null){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            GuestDto[] dtoGuests = weddingImportDto.getGuests();
            weddingImportDto.setGuests(null);

            Wedding wedding = this.mapper.map(weddingImportDto, Wedding.class);
            wedding.setBride(bride);
            wedding.setBridegroom(bridegroom);
            wedding.setAgency(agencies.get(0));

            Set<Invitation> invitations=new LinkedHashSet<>();
            for (GuestDto guestDto : dtoGuests) {

                Person guest = this.personRepository.findByFullName(guestDto.getName()).orElse(null);
                if(guest!=null){
                    Invitation invitation=new Invitation();
                    invitation.setGuest(guest);
                    invitation.setAttending(guestDto.getPresent());
                    invitation.setFamily(guestDto.getFamily());
                    invitation.setWedding(wedding);
                    invitations.add(invitation);
                }
            }
            wedding.setInvitations(invitations);
            this.weddingRepository.saveAndFlush(wedding);
            String brideName = weddingImportDto.getBride().split("\\s+")[0];
            String brideMannName = weddingImportDto.getBridegroom().split("\\s+")[0];
            res.append(String.format("Successfully imported wedding of %s and %s",
                    brideName,brideMannName))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
    @Override
    public void  setVenuesToWeddings(){
        List<Wedding> weddings = this.weddingRepository.findAll();
        for (Wedding wedding : weddings) {
            Set<Venue> randomVenues=this.getRandomVenues();
            wedding.setVenues(randomVenues);
            this.weddingRepository.save(wedding);
        }
    }

    private Set<Venue> getRandomVenues() {
        Set<Venue> randomvenues=new LinkedHashSet<>();
        long count = this.venueRepo.count();
        Random random=new Random();

        for (int i = 0; i <= 1; i++) {
            int randomId = random.nextInt((int) count)+1;
            Venue venue = this.venueRepo.findById(randomId).orElse(null);
            if(venue!=null){
                randomvenues.add(venue);
            }
        }
        return randomvenues;
    }
    @Override
    public WeddingExportDto[] query2(){
        List<Object[]> objects = this.weddingRepository.weddingStat();
        WeddingExportDto[] res=new WeddingExportDto[objects.size()];
        for (int i = 0; i < res.length; i++) {
            Object[] objects1 = objects.get(i);
            Wedding wedding = (Wedding) objects1[0];
            Long totalInvited = (Long) objects1[1];
            Long brideInvited = (Long) objects1[2];
            Long brideMannInvited = (Long) objects1[3];
            Long confirmed = (Long) objects1[4];
            AgencyGuestDto agencyGuestDto = this.mapper.map(wedding.getAgency(), AgencyGuestDto.class);

            WeddingExportDto currentWedding=new WeddingExportDto();


            currentWedding.setBride(wedding.getBride().getFullName());
            currentWedding.setBridegroom(wedding.getBridegroom().getFullName());
            currentWedding.setAgency(agencyGuestDto);
            currentWedding.setInvitedGuests(totalInvited);
            currentWedding.setBrideGuests(brideInvited);
            currentWedding.setBridegroomGuests(brideMannInvited);
            currentWedding.setAttendingGuests(confirmed);

            List<GuestExportDto> confirmedGuests=new ArrayList<>();
            for (Invitation invitation : wedding.getInvitations()) {
                if(invitation.getAttending()){
                    String fullName = invitation.getGuest().getFullName();
                    GuestExportDto curGuest=new GuestExportDto(fullName);
                    confirmedGuests.add(curGuest);
                }
            }
            GuestExportDto[] guestExportDtos=new GuestExportDto[confirmedGuests.size()];
            guestExportDtos =  confirmedGuests.toArray(guestExportDtos);
            currentWedding.setGuests(guestExportDtos);

            res[i]=currentWedding;
        }
        return res;
    }
}
