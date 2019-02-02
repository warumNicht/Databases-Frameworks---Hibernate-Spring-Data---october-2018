package photography.web;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import photography.domain.dto.PfotoStatDto;
import photography.domain.dto.PhotographerOrderedDto;
import photography.domain.dto.RootFotoDto;
import photography.domain.dto.importDto.CameraImportDto;
import photography.domain.dto.importDto.LensImportDto;
import photography.domain.dto.importDto.accsessories.AccessoryRootImportDto;
import photography.domain.dto.importDto.photographers.PhotographerImportDto;
import photography.domain.dto.importDto.workshops.WorkshopRootDto;
import photography.domain.dto.query3.RootFotoExportDto;
import photography.domain.dto.query4.LocRootExportDto;
import photography.services.interfaces.*;
import photography.util.FileUtil;
import photography.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;


@Controller
public class AppController implements CommandLineRunner {
    private final static String LENSES_FILE_PATH = "src\\main\\resources\\files\\lenses.json";
    private final static String CAMERAS_FILE_PATH = "src\\main\\resources\\files\\cameras.json";
    private final static String PHOTOGRAPHERS_FILE_PATH = "src\\main\\resources\\files\\photographers.json";

    private final static String WORKSHOPS_FILE_PATH = "src\\main\\resources\\files\\workshops.xml";
    private final static String ACCESSORIES_FILE_PATH = "src\\main\\resources\\files\\accessories.xml";

    private final static String PB1_FILE_PATH = "src\\main\\resources\\output\\photographers-ordered.json";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\output\\landscape-photogaphers.json";
    private final static String PB3_FILE_PATH = "src\\main\\resources\\output\\same-cameras-photographers.xml";
    private final static String PB4_FILE_PATH = "src\\main\\resources\\output\\workshops-by-location.xml";

    private Gson gson;
    private FileUtil fileUtil;
    private XmlParser xmlParser;

    private LensService lensService;
    private CameraService cameraService;
    private PhotographerService photographerService;
    private AccessoryService accessoryService;
    private WorkShopService workShopService;

    @Autowired
    public AppController(Gson gson, FileUtil fileUtil, XmlParser xmlParser, LensService lensService, CameraService cameraService, PhotographerService photographerService, AccessoryService accessoryService, WorkShopService workShopService) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.lensService = lensService;
        this.cameraService = cameraService;
        this.photographerService = photographerService;
        this.accessoryService = accessoryService;
        this.workShopService = workShopService;
    }

    @Override
    public void run(String... args) throws Exception {
//        importlenses();
//        importCameras();
//        importPhotos();
//        importAccessories();
//        importWorks();
              query1();
        query2();
        query3();
        query4();

    }

    private void query1() throws IOException {
        PhotographerOrderedDto[] photographerOrderedDtos = this.photographerService.query1();
        String res = this.gson.toJson(photographerOrderedDtos);
        this.fileUtil.write(res, PB1_FILE_PATH);
    }

    private void query2() throws IOException {
        PfotoStatDto[] pfotoStatDtos = this.photographerService.query2();
        String res = this.gson.toJson(pfotoStatDtos);
        this.fileUtil.write(res, PB2_FILE_PATH);
    }

    private void query3() throws IOException, JAXBException {
        RootFotoExportDto rootFotoExportDto = this.photographerService.query3();
        this.xmlParser.write(rootFotoExportDto, PB3_FILE_PATH);
    }
    private void query4() throws IOException, JAXBException {
        LocRootExportDto locRootExportDto = this.workShopService.query4();
        this.xmlParser.write(locRootExportDto, PB4_FILE_PATH);
    }

    private void importlenses() throws IOException {
        String content = this.fileUtil.read(LENSES_FILE_PATH);
        LensImportDto[] lensImportDtos = this.gson.fromJson(content, LensImportDto[].class);
        String res = this.lensService.importLenses(lensImportDtos);
        System.out.println(res);
    }

    private void importCameras() throws IOException {
        String content = this.fileUtil.read(CAMERAS_FILE_PATH);
        CameraImportDto[] cameraImportDtos = this.gson.fromJson(content, CameraImportDto[].class);
        String res = this.cameraService.importCameras(cameraImportDtos);
        System.out.println(res);
    }

    private void importPhotos() throws IOException {
        String content = this.fileUtil.read(PHOTOGRAPHERS_FILE_PATH);
        PhotographerImportDto[] photographerImportDtos = this.gson.fromJson(content, PhotographerImportDto[].class);
        String res = this.photographerService.importPhotographers(photographerImportDtos);
        System.out.println(res);
    }

    private void importAccessories() throws JAXBException, FileNotFoundException {
        AccessoryRootImportDto accessoryRootImportDto = this.xmlParser.read(AccessoryRootImportDto.class, ACCESSORIES_FILE_PATH);
        String res = this.accessoryService.importAccessories(accessoryRootImportDto);
        System.out.println(res);
    }

    private void importWorks() throws JAXBException, FileNotFoundException {
        WorkshopRootDto workshopRootDto = this.xmlParser.read(WorkshopRootDto.class, WORKSHOPS_FILE_PATH);
        String res = this.workShopService.importWorkshops(workshopRootDto);
        System.out.println(res);
    }


}
