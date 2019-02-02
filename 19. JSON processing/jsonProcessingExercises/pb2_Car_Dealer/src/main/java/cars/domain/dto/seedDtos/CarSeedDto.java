package cars.domain.dto.seedDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarSeedDto {
    @NotNull(message = "Make cannot be null!")
    @NotEmpty(message = "Make cannot be empty!" )
    private String make;

    @NotNull(message = "Model cannot be null!")
    @NotEmpty(message = "Model cannot be empty!" )
    private String model;

    @NotNull(message = "Distance cannot be null!")
    @Min(value = 0,message = "Distance cannot be negative!")
    private Long travelledDistance;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
