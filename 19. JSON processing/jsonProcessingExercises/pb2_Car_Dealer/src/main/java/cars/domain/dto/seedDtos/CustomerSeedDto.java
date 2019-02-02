package cars.domain.dto.seedDtos;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomerSeedDto implements Serializable {
    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!" )
    private String name;

    @NotNull(message = "Birth date cannot be null!")
    private Date birthDate;

    private boolean isYoungDriver;


    public CustomerSeedDto(String name, Date birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
