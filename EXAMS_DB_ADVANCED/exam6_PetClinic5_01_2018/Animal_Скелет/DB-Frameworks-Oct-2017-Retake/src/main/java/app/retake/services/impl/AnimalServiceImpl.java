package app.retake.services.impl;

import app.retake.domain.dto.importDtos.animalsPassports.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepository;
    private PassportRepository passportRepository;
    private ModelParser mapper;
@Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository,
                             PassportRepository passportRepository, ModelParser mapper) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        Animal animal =new Animal();
        animal.setAge(dto.getAge());
        animal.setName(dto.getName());
        animal.setType(dto.getType());

        Passport passport = new Passport();

        passport.setOwnerName(dto.getPassport().getOwnerName());
        passport.setOwnerPhoneNumber(dto.getPassport().getOwnerPhoneNumber());
        passport.setSerialNumber(dto.getPassport().getSerialNumber());
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(dto.getPassport().getRegistrationDate());
        passport.setRegistrationDate(date);
        this.passportRepository.saveAndFlush(passport);

        passport.setAnimal(animal);
        animal.setPassport(passport);
        this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        List<Animal> animals = this.animalRepository.query1(phoneNumber);

        List<AnimalsJSONExportDTO> res=new ArrayList<>();

        for (Animal animal : animals) {
            AnimalsJSONExportDTO current=new AnimalsJSONExportDTO();
            current.setAge(animal.getAge());
            current.setAnimalName(animal.getName());
            current.setOwnerName(animal.getPassport().getOwnerName());
            current.setSerialNumber(animal.getPassport().getSerialNumber());
            Date registrationDate = animal.getPassport().getRegistrationDate();
            SimpleDateFormat format=new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = format.format(registrationDate);
            current.setRegisteredOn(dateStr);

            res.add(current);
        }
        return res;
    }
}
