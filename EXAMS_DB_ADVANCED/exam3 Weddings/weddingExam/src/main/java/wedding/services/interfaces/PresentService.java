package wedding.services.interfaces;

import wedding.domain.dto.importDto.importPresents.PresentRootDto;

public interface PresentService {
    String importPresents(PresentRootDto presentRootDto);
}
