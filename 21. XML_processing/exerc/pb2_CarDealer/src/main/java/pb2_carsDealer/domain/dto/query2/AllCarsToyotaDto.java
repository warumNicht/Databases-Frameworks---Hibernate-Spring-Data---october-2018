package pb2_carsDealer.domain.dto.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "cars")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllCarsToyotaDto {
    @XmlElement(name = "car")
    private List<CarModelDto>  carModelDtos;

    public List<CarModelDto> getCarModelDtos() {
        return carModelDtos;
    }

    public void setCarModelDtos(List<CarModelDto> carModelDtos) {
        this.carModelDtos = carModelDtos;
    }
}
