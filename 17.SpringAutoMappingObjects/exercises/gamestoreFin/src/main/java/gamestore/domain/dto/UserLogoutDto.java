package gamestore.domain.dto;

public class UserLogoutDto {
    private String email;

    public UserLogoutDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
