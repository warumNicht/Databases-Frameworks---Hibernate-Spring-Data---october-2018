package wedding.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wedding.domain.dto.importDto.importVenues.VenueImportDto;
import wedding.domain.dto.importDto.importVenues.VenuImportRootDto;
import wedding.domain.dto.query3.VenuSofiaDto;
import wedding.domain.dto.query3.VenueExportRootDto;
import wedding.domain.entities.Venue;
import wedding.repositories.VenueRepo;
import wedding.services.interfaces.VenueService;
import wedding.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private VenueRepo venueRepo;
    private ModelMapper mapper;
@Autowired
    public VenueServiceImpl(VenueRepo venueRepo, ModelMapper mapper) {
        this.venueRepo = venueRepo;
        this.mapper = mapper;
    }
    @Override
    public String importVenues(VenuImportRootDto VenuImportRootDto){
        StringBuilder res=new StringBuilder();
        for (VenueImportDto venueImportDto : VenuImportRootDto.getVenueImportDtos()) {
            Venue venue = this.mapper.map(venueImportDto, Venue.class);
            this.venueRepo.saveAndFlush(venue);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,
                    venue.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
    @Override
    public VenueExportRootDto query3(){
        List<Venue> venues = this.venueRepo.venuesFromSofia();
        VenueExportRootDto res =new VenueExportRootDto();
        if(venues.isEmpty()){
            return res;
        }
        res.setTown(venues.get(0).getTown());
        List<VenuSofiaDto> venuSofiaDtos=new ArrayList<>();
        for (Venue venue : venues) {
            VenuSofiaDto currentVen=new VenuSofiaDto();
            currentVen.setName(venue.getName());
            currentVen.setCapacity(venue.getCapacity());
            currentVen.setCount(venue.getWeddings().size());
            venuSofiaDtos.add(currentVen);
        }
        res.setVenuSofiaDtos(venuSofiaDtos);
        return res;
    }
}
