package wedding.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wedding.domain.dto.importDto.PeopleImportDto;
import wedding.domain.entities.Person;
import wedding.repositories.PersonRepository;
import wedding.services.interfaces.PersonService;
import wedding.util.TextUtil;
import wedding.util.ValidationUtil;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;
@Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper mapper, ValidationUtil validationUtil) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    this.validationUtil = validationUtil;
}
    @Override
    public String importPersons(PeopleImportDto[] peopleImportDtos){
        StringBuilder res=new StringBuilder();
        for (PeopleImportDto peopleImportDto : peopleImportDtos) {
            if(!this.validationUtil.isValid(peopleImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Person person = this.mapper.map(peopleImportDto, Person.class);
            this.personRepository.saveAndFlush(person);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,person.getFullName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
