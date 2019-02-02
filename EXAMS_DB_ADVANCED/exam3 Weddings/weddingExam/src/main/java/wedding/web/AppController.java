package wedding.web;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import wedding.domain.dto.importDto.AgencyExportDto;
import wedding.domain.dto.importDto.PeopleImportDto;
import wedding.domain.dto.importDto.importPresents.PresentRootDto;
import wedding.domain.dto.importDto.importVenues.VenuImportRootDto;
import wedding.domain.dto.importDto.weddingImport.WeddingImportDto;
import wedding.domain.dto.query2.WeddingExportDto;
import wedding.domain.dto.query3.VenueExportRootDto;
import wedding.domain.dto.query4.TownAllRootDto;
import wedding.services.interfaces.*;
import wedding.util.FileUtil;
import wedding.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {
    private final static String AGENCIES_FILE_PATH = "src\\main\\resources\\imputFiles\\agencies.json";
    private final static String PEOPLE_FILE_PATH = "src\\main\\resources\\imputFiles\\people.json";
    private final static String WEDDINGS_FILE_PATH = "src\\main\\resources\\imputFiles\\weddings.json";

    private final static String PRESENTS_FILE_PATH = "src\\main\\resources\\imputFiles\\presents.xml";
    private final static String VENUES_FILE_PATH = "src\\main\\resources\\imputFiles\\venues.xml";

    private final static String PB1_FILE_PATH = "src\\main\\resources\\output\\agencies-ordered.json";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\output\\guests.json";
    private final static String PB3_FILE_PATH = "src\\main\\resources\\output\\sofia-venues.xml";
    private final static String PB4_FILE_PATH = "src\\main\\resources\\output\\agencies-by-town.xml";

    private AgencyService agencyService;
    private PersonService personService;
    private WeddingService weddingService;
    private VenueService venueService;
    private PresentService presentService;
    private Gson gson;
    private FileUtil fileUtil;
    private XmlParser xmlParser;

    @Autowired
    public AppController(AgencyService agencyService, PersonService personService,
                         WeddingService weddingService, VenueService venueService, PresentService presentService, Gson gson, FileUtil fileUtil, XmlParser xmlParser) {
        this.agencyService = agencyService;
        this.personService = personService;
        this.weddingService = weddingService;
        this.venueService = venueService;
        this.presentService = presentService;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.importAgencies();
//        this.importPersons();
//        this.importWeddings();
//        this.importVenues();
//        this.weddingService.setVenuesToWeddings();
//        this.importPresents();
//
//        this.query1();
//        this.query2();
        this.query3();
        this.query4();

    }

    private void importAgencies() throws IOException {
        String content = this.fileUtil.read(AGENCIES_FILE_PATH);
        AgencyExportDto[] agencyImportDtos = this.gson.fromJson(content, AgencyExportDto[].class);
        String res = this.agencyService.importAgencies(agencyImportDtos);
        System.out.println(res);
    }

    private void importPersons() throws IOException {
        String content = this.fileUtil.read(PEOPLE_FILE_PATH);
        PeopleImportDto[] peopleImportDtos = this.gson.fromJson(content, PeopleImportDto[].class);
        String res = this.personService.importPersons(peopleImportDtos);
        System.out.println(res);
    }

    private void importWeddings() throws IOException {
        String content = this.fileUtil.read(WEDDINGS_FILE_PATH);
        WeddingImportDto[] weddingImportDtos = this.gson.fromJson(content, WeddingImportDto[].class);
        String res = this.weddingService.importWeddings(weddingImportDtos);
        System.out.println(res);
    }

    private void importVenues() throws IOException, JAXBException {
        VenuImportRootDto VenuImportRootDto = this.xmlParser.read(VenuImportRootDto.class, VENUES_FILE_PATH);
        String res = this.venueService.importVenues(VenuImportRootDto);
        System.out.println(res);
    }

    private void importPresents() throws IOException, JAXBException {
        PresentRootDto presentRootDto = this.xmlParser.read(PresentRootDto.class, PRESENTS_FILE_PATH);
        String res = this.presentService.importPresents(presentRootDto);
        System.out.println(res);
    }

    private void query1() throws IOException {
        AgencyExportDto[] agencyExportDtos = this.agencyService.query1();
        String resContent = this.gson.toJson(agencyExportDtos);
        this.fileUtil.write(resContent, PB1_FILE_PATH);
    }

    private void query2() throws IOException{
        WeddingExportDto[] weddingExportDtos = this.weddingService.query2();
        String resContent = this.gson.toJson(weddingExportDtos);
        this.fileUtil.write(resContent, PB2_FILE_PATH);
    }

    private void query3() throws JAXBException {
        VenueExportRootDto venueExportRootDto = this.venueService.query3();
        this.xmlParser.write(venueExportRootDto, PB3_FILE_PATH);
    }

    private void query4() throws JAXBException {
        TownAllRootDto townAllRootDto = this.agencyService.query4();
        this.xmlParser.write(townAllRootDto, PB4_FILE_PATH);
    }
}
