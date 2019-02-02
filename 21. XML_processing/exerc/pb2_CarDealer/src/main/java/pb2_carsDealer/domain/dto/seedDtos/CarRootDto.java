package pb2_carsDealer.domain.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CarRootDto {
    @XmlElement(name = "car")
    private List<CarSeedDto> carSeedDtos;

    public List<CarSeedDto> getCarSeedDtos() {
        return carSeedDtos;
    }

    public void setCarSeedDtos(List<CarSeedDto> carSeedDtos) {
        this.carSeedDtos = carSeedDtos;
    }
}
