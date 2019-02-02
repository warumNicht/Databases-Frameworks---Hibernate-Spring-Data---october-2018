package mostwanted.service.interfaces;

import java.io.IOException;

public interface DistrictService {

    Boolean districtsAreImported();

    String readDistrictsJsonFile() throws IOException;

    String importDistricts(String districtsFileContent);
}
