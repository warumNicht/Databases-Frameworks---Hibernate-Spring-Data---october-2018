package pb2_carsDealer.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "car")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarWithPartsDto {
    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartDto> parts;

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

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
