package org.softuni.mostwanted.domain.dto.exportDto;

public class RacingTownDto {
    private String name;
    private Integer racers;

    public RacingTownDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
