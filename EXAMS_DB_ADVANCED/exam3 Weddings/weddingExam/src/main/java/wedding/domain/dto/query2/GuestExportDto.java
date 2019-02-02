package wedding.domain.dto.query2;

public class GuestExportDto {

    public GuestExportDto(String fullName) {
        this.fullName = fullName;
    }

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
