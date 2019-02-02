package org.softuni.mostwanted.domain.dto.exportDto;

import java.util.List;

public class RacerCarsDto {
    private String name;
    private Integer age;
    private List<RacingCarDto> cars;

    public RacerCarsDto() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<RacingCarDto> getCars() {
        return cars;
    }

    public void setCars(List<RacingCarDto> cars) {
        this.cars = cars;
    }
}
