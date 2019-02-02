package mostwanted.service.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface RaceEntryService {

    Boolean raceEntriesAreImported();

    String readRaceEntriesXmlFile() throws IOException;

    String importRaceEntries() throws JAXBException;
}
