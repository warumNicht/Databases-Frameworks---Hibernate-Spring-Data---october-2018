package pb4_Hospital.core;

import pb4_Hospital.entities.*;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class JPManager implements Runnable {
    private final String ROW_OF_STARS = "***********************************************************************************";
    private final String ROW_OF_TIRETS = "-----------------------------------------------------------------------------------";
    private EntityManager manager;
    private Scanner scanner;

    public JPManager(EntityManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        System.out.println(ROW_OF_STARS);
        System.out.println(String.format("%s %60s",
                "Wellcome in JP System!", "Willkommen in unseres System!"));
        ;
        System.out.println(ROW_OF_STARS);
        while (true) {
            System.out.println(ROW_OF_TIRETS);
            System.out.println("Choose command: new entity   - n , statistics - s,        exit  - e ");
            System.out.println(ROW_OF_TIRETS);

            String firstComm = scanner.nextLine().toLowerCase();
            switch (firstComm) {
                case "n": {
                    this.createEntity();
                }
                break;
                case "e": {
                    System.out.println(ROW_OF_STARS);
                    System.out.println(String.format("%s %52s",
                            "Tank you for using our service!", "Danke dass Sie unseres System benutzt haben!"));
                    ;
                    System.out.println(ROW_OF_STARS);
                    return;
                }
                case "s": {
                    this.getStatistics();
                }
                break;
            }
        }

    }

    private void getStatistics() {
        System.out.println("Single patient   - p , all visitations - v,     all patients  - all");

        String comm = scanner.nextLine();

        switch (comm) {
            case "p": {
                this.getStatisticsSinglePatient();
            }
            break;
            case "v": {
                this.printAllVisitationsInLastMonthOrderedByDateDesc();
            }
            break;
            case "all": {
                this.printAllPatientsByBirthdayAsc();
            }
            break;

        }
    }

    private void printAllPatientsByBirthdayAsc() {
        List<Patient> patients = this.manager
                .createQuery("SELECT p FROM Patient p ORDER BY p.dateOfBirth ASC ", Patient.class)
                .getResultList();
        StringBuilder res=new StringBuilder();
        res.append(ROW_OF_STARS).append(System.lineSeparator());

        if(patients.isEmpty()){
            res.append("No patients");
        }else {
            res.append("Registered patients:").append(System.lineSeparator());
            int index=1;
            for (Patient patient : patients) {
                res.append(String.format("%d. %s",index++,patient))
                        .append(System.lineSeparator());
            }
        }
        System.out.println(res.toString().trim());
    }

    private void printAllVisitationsInLastMonthOrderedByDateDesc() {
        Date dateBeforeOneMonth=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dateBeforeOneMonth);
        calendar.add(Calendar.MONTH,-1);
        dateBeforeOneMonth=calendar.getTime();

        this.manager.getTransaction().commit();
        this.manager.getTransaction().begin();
        List<Visitation> lastVisitations = this.manager
                .createQuery("SELECT v FROM Visitation v WHERE v.date > :date ORDER BY v.date DESC ", Visitation.class)
                .setParameter("date",dateBeforeOneMonth)
                .getResultList();

        StringBuilder res=new StringBuilder();
        res.append(ROW_OF_STARS).append(System.lineSeparator());

        if(lastVisitations.isEmpty()){
            res.append("No visitations in last month");
        }else {
            res.append("Last month visitations:").append(System.lineSeparator());
            int index=1;
            for (Visitation lastVisitation : lastVisitations) {
                res.append(String.format("%d. %s",index++,lastVisitation))
                        .append(System.lineSeparator());
            }
        }
        System.out.println(res.toString().trim());
    }

    private void getStatisticsSinglePatient() {
        try {
            Patient patient = this.getSinglePatient();
            StringBuilder res = new StringBuilder();
            res.append("Visitations:");
            this.manager.refresh(patient);

            if (patient.getVisitations().isEmpty()) {
                res.append(" no");
                System.out.println(ROW_OF_STARS);
                System.out.println(patient);
                System.out.println(res);
                return;
            }
            res.append(System.lineSeparator());
            String pattern = "dd-MMM-yyyy  hh:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            int[] index = new int[1];
            patient.getVisitations().stream().sorted((x, y) -> {
                return y.getDate().compareTo(x.getDate());
            }).forEach(x -> {
                res.append(String.format("  %d. Visitation on: %s%n", ++index[0],
                        simpleDateFormat.format(x.getDate())));
                res.append(String.format("  Diagnose: %s", x.getDiagnose().getName()));
                res.append(String.format("  Diagnose comment: %s%n",x.getDiagnose().getComments()));

                List<String> medicaments = x.getDiagnose().getMedicaments()
                        .stream()
                        .map(y -> y.getName())
                        .collect(Collectors.toList());
                String listOfMedicaments = String.join(", ", medicaments);

                res.append(String.format("  Medikaments: %s%n",
                        listOfMedicaments.equals("") ? " no" : listOfMedicaments));
                res.append(String.format("  Comment: %s%n", x.getComments()));
            });
            System.out.println(ROW_OF_STARS);
            System.out.println(patient);
            System.out.println(res);

        } catch (Exception e) {

        }
    }

    private void createEntity() {
        System.out.println("medicament   - m ,   patient - p,        visitation  - v");
        System.out.println(ROW_OF_TIRETS);

        String comm = scanner.nextLine().toLowerCase();

        switch (comm.toLowerCase()) {
            case "m": {
                this.createMedicament();
            }
            break;
            case "p": {
                this.createPatient();
            }
            break;
            case "v": {
                this.createVisitation();
            }
            break;

        }
    }

    private void createVisitation() {
        Patient patient = patient = this.getSinglePatient();
        if (patient == null) {
            return;
        }
        System.out.println("Enter Diagnose name:");
        String diagnoseName = scanner.nextLine();
        System.out.println("Enter comment:");
        String diagnoseKomm = scanner.nextLine();
        Diagnose diagnose = new Diagnose(diagnoseName, diagnoseKomm);

        System.out.println("Choose List of medicaments:  (comma separated)");
        String listOfMedicaments = scanner.nextLine().trim();
        Set<Medicament> medicamentsToDiagnose = this.getListOfMedicaments(listOfMedicaments);

        diagnose.setMedicaments(medicamentsToDiagnose);

        System.out.println("Enter comments for the visitation:");
        String besuchKommentar = scanner.nextLine();

        Visitation visitation = new Visitation(new Date(), besuchKommentar, patient, diagnose);

        this.manager.persist(visitation);
        this.manager.getTransaction().commit();
        this.manager.getTransaction().begin();
        System.out.println(ROW_OF_STARS);
        System.out.printf("The visitation of patient %s %s was saved in the database%n", patient.getFirstName(), patient.getLastName());
    }

    private Patient getSinglePatient() {
        System.out.println("Select patient:  (email)");
        String patientEmail = scanner.nextLine();

        try {
            Patient patient = (Patient) this.manager.createQuery("SELECT p FROM Patient p WHERE p.email=?1")
                    .setParameter(1, patientEmail)
                    .getSingleResult();
            return patient;
        } catch (Exception e) {
            System.out.printf("Patient with Email %s does not exists%n", patientEmail);
        }

        return null;
    }

    private Set<Medicament> getListOfMedicaments(String listOfMedicaments) {
        String[] stringNames = listOfMedicaments.split(",\\s+");
        Set<Medicament> medicaments = new HashSet<>();

        Medicament currMedicament = null;

        for (String medikamentName : stringNames) {
            try {
                currMedicament = this.findMedicament(medikamentName);
                medicaments.add(currMedicament);
            } catch (Exception e) {
                System.out.printf("Medikament %s does not exists!%n", medikamentName);

                System.out.println("Do you want to save it in the database?    Y/N");
                String choose = this.scanner.nextLine();
                if(choose.equals("Y")){
                    this.persistMedicament(medikamentName);
                    try {
                        Medicament medicament = this.findMedicament(medikamentName);
                        medicaments.add(medicament);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return medicaments;
    }

    private Medicament addSingleMedicamentToDiagnose() {
        while (true) {
            System.out.println("Choose another Medikament or skip (enter)");
            String newChoice = scanner.nextLine();
            if (newChoice.equals("")) {
                return null;
            }
            try {
                Medicament medicament = this.findMedicament(newChoice);
                return medicament;
            } catch (Exception e1) {
                continue;
            }
        }
    }

    private Medicament findMedicament(String medicament) throws Exception {
        Medicament singleResult = (Medicament) this.
                manager.createQuery("SELECT m FROM Medicament m WHERE m.name=?1").setParameter(1, medicament)
                .getSingleResult();
        return singleResult;
    }

    private void createPatient() {
        System.out.println("Enter first name, last name and  Email:");
        String[] names = scanner.nextLine().split("\\s+");
        String firstName = names[0];
        String lastName = names[1];
        String email = names[2];
        try {
            Patient singleResult = (Patient) this.
                    manager.createQuery("SELECT p FROM Patient p WHERE p.email=?1")
                    .setParameter(1, email)
                    .getSingleResult();
            System.out.println(ROW_OF_STARS);
            System.out.println(String.format("Der Patient %s with Email %s already exists", firstName,email));
        } catch (Exception e) {
            System.out.println("Enter address:");
            String address = scanner.nextLine();

            System.out.println("Enter birthday dd-MM-yyyy");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            format.setLenient(false);
            Date dateOfBirth = null;
            while (true) {
                String dateString = scanner.nextLine();
                try {
                    dateOfBirth = format.parse(dateString.trim());
                    break;
                } catch (ParseException e1) {
                    System.out.println("Incorrekt Datum Format, geben Sie noch einmal ein!");
                }
            }
            System.out.println("Enter photo (url):");
            String picture = scanner.nextLine();

            Address addressToSet=this.getAddressFromDatabase(address);

            Patient patient = new Patient(firstName, lastName, addressToSet, email, dateOfBirth, picture);

            System.out.println("Has medical insurance or not Y/N");
            String gesichert = scanner.nextLine();
            if (gesichert.equals("Y")) {
                patient.setInsured(true);
            }

            this.manager.persist(patient);
            this.manager.getTransaction().commit();
            this.manager.getTransaction().begin();
            System.out.println(ROW_OF_STARS);
            System.out.printf("Patient %s %s was entered into the database%n", firstName, lastName);
        }
    }

    private Address getAddressFromDatabase(String address) {
        try {
            Address result = this.manager.createQuery("select a FROM Address a WHERE a.name=:name", Address.class)
                    .setParameter("name", address)
                    .getSingleResult();
            return result;
        }catch (Exception e){
            Address address1=new Address(address);
           return address1;
        }
    }

    private void createMedicament() {
        System.out.println("Enter medicament name");
        String medikamentName = scanner.nextLine();

        try {
            Medicament singleResult = this.findMedicament(medikamentName);
            System.out.println(ROW_OF_STARS);
            System.out.println(String.format("Das Medikament \"%s\" existiert schon", medikamentName));
        } catch (Exception e) {
            this.persistMedicament(medikamentName);
        }

    }

    private void persistMedicament(String medikamentName) {
        Medicament currentMedicament = new Medicament(medikamentName);
        this.manager.persist(currentMedicament);
        this.manager.getTransaction().commit();
        this.manager.getTransaction().begin();
        System.out.println(ROW_OF_STARS);
        System.out.printf("The medicament \"%s\" was stored into the database%n", medikamentName);
    }
}
