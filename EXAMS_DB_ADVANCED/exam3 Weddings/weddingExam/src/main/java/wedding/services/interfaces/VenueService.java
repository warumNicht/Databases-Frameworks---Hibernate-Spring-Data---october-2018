package wedding.services.interfaces;

import wedding.domain.dto.importDto.importVenues.VenuImportRootDto;
import wedding.domain.dto.query3.VenueExportRootDto;

public interface VenueService {
    String importVenues(VenuImportRootDto VenuImportRootDto);

    VenueExportRootDto query3();
}
