package wedding.domain.dto.importDto;

import com.google.gson.annotations.SerializedName;

public class AgencyExportDto {
    private String name;
    @SerializedName(value = "employeesCount")
    private Integer employeeCount;
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
