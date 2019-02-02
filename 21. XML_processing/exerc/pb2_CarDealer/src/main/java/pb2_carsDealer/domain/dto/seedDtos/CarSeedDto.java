package pb2_carsDealer.domain.dto.seedDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarSeedDto {
    @XmlElement(name = "make")
    @NotNull(message = "Make cannot be null!")
    @NotEmpty(message = "Make cannot be empty!" )
    private String make;

    @XmlElement(name = "model")
    @NotNull(message = "Model cannot be null!")
    @NotEmpty(message = "Model cannot be empty!" )
    private String model;

    @XmlElement(name = "travelled-distance")
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
