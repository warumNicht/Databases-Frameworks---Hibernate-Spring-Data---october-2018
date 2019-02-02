package wedding.services.interfaces;

import wedding.domain.dto.importDto.PeopleImportDto;



public interface PersonService {
    String importPersons(PeopleImportDto[] peopleImportDtos);


}
