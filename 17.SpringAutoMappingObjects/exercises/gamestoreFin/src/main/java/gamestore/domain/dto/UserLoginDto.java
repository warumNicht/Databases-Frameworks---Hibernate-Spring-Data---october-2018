package gamestore.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginDto {
    @NotNull(message = "Email can not be null!")
    @Pattern(regexp = "^([a-zA-Z0-9]+)([.\\-_][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.\\-_][a-zA-Z0-9]+)+$",
            message = "Invalid email")
    private String email;

    @NotNull(message = "Password can not be null!")
    @Pattern(regexp = "^(?=.*[a-z]).+$",message = "Password should contain at least one lowercase letter!")
    @Pattern(regexp = "^(?=.*[A-Z]).+$",message = "Password should contain at least one uppercase letter!")
    @Pattern(regexp = "^(?=.*[0-9]).+$",message = "Password should contain at least one digit!")
    @Pattern(regexp = "^.{6,}$",message = "Password should have at least 6 symbols!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$", message = "Invalid password!")
    private String password;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
