package org.softuni.mostwanted.domain.dto.exportDto;

public class RacingCarDto {
    private String car;

    public RacingCarDto(String car) {
        this.car = car;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
