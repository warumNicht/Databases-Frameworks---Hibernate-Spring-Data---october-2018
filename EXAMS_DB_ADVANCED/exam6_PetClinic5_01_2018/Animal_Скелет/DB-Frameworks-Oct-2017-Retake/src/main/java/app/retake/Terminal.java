package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Component
public class Terminal implements CommandLineRunner {
    public static final String ANIMAL_AIDS_IMPORT_JSON = "src\\main\\resources\\files\\json\\animal_aids.json";
    public static final String ANIMALS_IMPORT_JSON = "src\\main\\resources\\files\\json\\animals.json";
    public static final String VETS_IMPORT_XML = "src\\main\\resources\\files\\xml\\vets.xml";
    public static final String PROCEDURES_IMPORT_XML = "src\\main\\resources\\files\\xml\\procedures.xml";

    public static final String PB1_FILE_PATH = "src\\main\\resources\\output\\query1.json";
    public static final String PB2_FILE_PATH = "src\\main\\resources\\output\\query2.xml";

    private AnimalAidController animalAidController;
    private AnimalController animalController;
    private ProcedureController procedureController;
    private VetController vetController;
    private FileIO fileIO;

    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController,
                    ProcedureController procedureController, VetController vetController, FileIO fileIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.procedureController = procedureController;
        this.vetController = vetController;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... strings) throws Exception {
        importAids();
        importAnimals();
        importVetss();
        importProcedures();
        query1();
        query2();
    }

    private void importAids() throws IOException {
        String content = this.fileIO.read(ANIMAL_AIDS_IMPORT_JSON);
        String res = this.animalAidController.importDataFromJSON(content);
        System.out.println(res);
    }

    private void importAnimals() throws IOException, ParseException {
        String content = this.fileIO.read(ANIMALS_IMPORT_JSON);
        String res = this.animalController.importDataFromJSON(content);
        System.out.println(res);
    }

    private void importVetss() throws IOException, ParseException, JAXBException {
        String content = this.fileIO.read(VETS_IMPORT_XML);
        String res = this.vetController.importDataFromXML(content);
        System.out.println(res);
    }

    private void importProcedures() throws IOException, ParseException, JAXBException {
        String content = this.fileIO.read(PROCEDURES_IMPORT_XML);
        String res = this.procedureController.importDataFromXML(content);
        System.out.println(res);
    }

    private void query1() throws IOException {
        String res = this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123");
        this.fileIO.write(res, PB1_FILE_PATH);
    }

    private void query2() throws IOException, JAXBException {
        String res = this.procedureController.exportProcedures();
        this.fileIO.write(res, PB2_FILE_PATH);
    }
}
