package wedding.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wedding.domain.dto.importDto.AgencyExportDto;
import wedding.domain.dto.query4.*;
import wedding.domain.entities.Agency;
import wedding.domain.entities.Invitation;
import wedding.domain.entities.Person;
import wedding.domain.entities.Wedding;
import wedding.repositories.AgencyRepository;
import wedding.repositories.WeddingRepository;
import wedding.services.interfaces.AgencyService;
import wedding.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    private AgencyRepository agencyRepository;
    private WeddingRepository weddingRepository;
    private ModelMapper mapper;

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository, WeddingRepository weddingRepository, ModelMapper mapper) {
        this.agencyRepository = agencyRepository;
        this.weddingRepository = weddingRepository;
        this.mapper = mapper;
    }

    @Override
    public String importAgencies(AgencyExportDto[] agencyImportDtos) {
        StringBuilder res = new StringBuilder();
        for (AgencyExportDto agencyImportDto : agencyImportDtos) {
            Agency agency = this.mapper.map(agencyImportDto, Agency.class);
            this.agencyRepository.saveAndFlush(agency);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM, agency.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public AgencyExportDto[] query1() {
        List<Agency> allByOrOrdered = this.agencyRepository.findAllByIdOrOrderByEmployeeCountDescNameAsc();
        AgencyExportDto[] res = new AgencyExportDto[allByOrOrdered.size()];
        for (int i = 0; i < res.length; i++) {
            Agency agency = allByOrOrdered.get(i);
            AgencyExportDto exportDto = this.mapper.map(agency, AgencyExportDto.class);
            res[i] = exportDto;
        }
        return res;
    }

    @Override
    public TownAllRootDto query4() {
        TownAllRootDto res = new TownAllRootDto();
        List<TownWithAgenciesDto> townsAg=new ArrayList<>();

        List<Object> towns = this.agencyRepository.findTowns();

        for (Object town : towns) {
            List<Agency> byTownAndWeddingCount = this.agencyRepository
                    .getByTownAndWeddingCount((String) town);

            if(!byTownAndWeddingCount.isEmpty()){
                TownWithAgenciesDto townWithAgenciesDto=this.createTownWithAgencies(byTownAndWeddingCount);
                townsAg.add(townWithAgenciesDto);
            }
        }
        res.setTownWithAgenciesDtos(townsAg);
        return res;
    }

    private TownWithAgenciesDto createTownWithAgencies(List<Agency> byTownAndWeddingCount) {
        TownWithAgenciesDto res=new TownWithAgenciesDto();
        res.setName(byTownAndWeddingCount.get(0).getTown());

        List<TownAgencyDto> townAgencyDtos=new ArrayList<>();
        for (Agency agency : byTownAndWeddingCount) {
            TownAgencyDto currentAgency=this.createAgency(agency);
            townAgencyDtos.add(currentAgency);
        }
        res.setTownAgencyDtos(townAgencyDtos);
        return res;
    }

    private TownAgencyDto createAgency(Agency agency) {
        TownAgencyDto res=new TownAgencyDto();

        List<TownWeddingDto> townWeddingDtos=new ArrayList<>();
        for (Wedding wedding : agency.getWeddings()) {
            TownWeddingDto weddingDto=this.createWeddig(wedding);
            townWeddingDtos.add(weddingDto);
        }
        res.setWeddingDtos(townWeddingDtos);

        res.setName(agency.getName());
        Double profit=this.calculateAgencyprofit(res);
        res.setProfit(String.format("%.3f",profit));
        return res;
    }

    private TownWeddingDto createWeddig(Wedding wedding) {
        TownWeddingDto res=new TownWeddingDto();
        res.setBride(wedding.getBride().getFullName());
        res.setBridegroom(wedding.getBridegroom().getFullName());
        long cash=this.weddingCash(wedding);
        res.setCash(cash);
        long countOfGifts=this.countWeddingGifts(wedding);
        res.setPresents((int)countOfGifts);
        List<TownGuestDto> finalGuests=new ArrayList<>();
        for (Invitation invitation : wedding.getInvitations()) {
            Person guest = invitation.getGuest();
            TownGuestDto guestDto = this.mapper.map(guest, TownGuestDto.class);
            guestDto.setFamily(invitation.getFamily());
            finalGuests.add(guestDto);
        }
        res.setGuests(finalGuests);
        return res;
    }

    private long countWeddingGifts(Wedding wedding) {
        long res=this.weddingRepository.getGiftCount(wedding.getId());
        return res;
    }

    private long weddingCash(Wedding wedding) {
        Integer id = wedding.getId();
        Object cash = this.weddingRepository.getCash(id);
        long res =(long)cash;
        return res;
    }

    private Double calculateAgencyprofit(TownAgencyDto agency) {
        long sum = agency.getWeddingDtos().stream()
                .mapToLong(w -> w.getCash())
                .sum();
        return sum*0.2;
    }
}
