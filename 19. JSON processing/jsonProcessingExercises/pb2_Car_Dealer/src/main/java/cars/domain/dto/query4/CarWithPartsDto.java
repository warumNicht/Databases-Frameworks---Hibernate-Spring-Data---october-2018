package cars.domain.dto.query4;

import java.util.List;

public class CarWithPartsDto {
    private CarDto car;
    private List<PartDto> parts;


    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
