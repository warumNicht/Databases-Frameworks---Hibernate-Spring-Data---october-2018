package org.softuni.mostwanted.domain.entities;

import javax.persistence.*;

@Entity(name = "race_entries")
public class RaceEntry extends BaseEntity {
    @Column(name = "has_finished")
    private Boolean hasFinished;

    @Column(name = "finished_time")
    private Double finishedTime;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "racer_id",referencedColumnName = "id")
    private Racer racer;

    public RaceEntry() {
    }

    public RaceEntry(Boolean hasFinished, Double finishedTime, Car car, Racer racer) {
        this.hasFinished = hasFinished;
        this.finishedTime = finishedTime;
        this.car = car;
        this.racer = racer;
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Double finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
