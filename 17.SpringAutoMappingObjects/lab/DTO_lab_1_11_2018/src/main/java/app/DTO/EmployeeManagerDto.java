package app.DTO;

public class EmployeeManagerDto extends EmployeeDto {
    private String manager;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
