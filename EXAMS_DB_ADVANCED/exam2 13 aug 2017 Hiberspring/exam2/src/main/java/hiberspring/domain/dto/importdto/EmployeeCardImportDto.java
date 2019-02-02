package hiberspring.domain.dto.importdto;

import javax.validation.constraints.NotNull;

public class EmployeeCardImportDto {
    @NotNull
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
