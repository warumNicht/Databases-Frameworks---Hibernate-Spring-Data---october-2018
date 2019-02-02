package hiberspring.services;

import hiberspring.domain.dto.importdto.EmployeeCardImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repositories.CardRepository;
import hiberspring.services.interfaces.CardService;
import hiberspring.util.TextUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository,
                           ValidationUtil validationUtil, ModelMapper mapper) {
        this.cardRepository = cardRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public String importCards(EmployeeCardImportDto[] employeeCardImportDtos) {
        StringBuilder res = new StringBuilder();
        for (EmployeeCardImportDto employeeCardImportDto : employeeCardImportDtos) {
            if (!this.validationUtil.isValid(employeeCardImportDto)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            EmployeeCard card = this.cardRepository.findByNumber(employeeCardImportDto.getNumber()).orElse(null);
            if (card != null) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            EmployeeCard employeeCard = this.mapper.map(employeeCardImportDto, EmployeeCard.class);
            this.cardRepository.saveAndFlush(employeeCard);

            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM, employeeCard.getNumber()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public EmployeeCardImportDto[] findCardsWithoutUser() {
        List<EmployeeCard> byEmployeeIsNull = this.cardRepository
                .cardWithoutEmployeeNativeReturnEntities();

        List<EmployeeCard> employeeCards = this.cardRepository.cardWitoutEmployee2();

        List<Object> objects = this.cardRepository.cardWithoutEmployeeReturnsObject();

        List<EmployeeCard> byEmployeeIsNull1 = this.cardRepository.findByEmployeeIsNull();
        EmployeeCardImportDto[] res = new EmployeeCardImportDto[byEmployeeIsNull.size()];
        for (int i = 0; i < res.length; i++) {
            EmployeeCard card = byEmployeeIsNull.get(i);
            EmployeeCardImportDto map = this.mapper.map(card, EmployeeCardImportDto.class);
            res[i] = map;
        }
        return res;
    }
}
