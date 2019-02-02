package org.softuni.mostwanted.domain.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "racers")
public class Racer extends BaseEntityWithName {

    @Column(name = "age")
    private Integer age;

    @Column(name = "bounty")
    private BigDecimal bounty;

    @ManyToOne
    @JoinColumn(name = "home_town_id",referencedColumnName = "id")
    private Town homeTown;

    @OneToMany(mappedBy = "driver",fetch = FetchType.EAGER)
 //   @Fetch(value = FetchMode.SUBSELECT)
    private Set<Car> cars;
//
    @OneToMany(mappedBy = "racer")
    private Set<RaceEntry> entries;

    public Racer() {
        this.cars=new LinkedHashSet<>();
        this.entries=new LinkedHashSet<>();
    }

    public Racer(String name, Integer age, BigDecimal bounty, Town homeTown) {
        super(name);
        this.age = age;
        this.bounty = bounty;
        this.homeTown = homeTown;
        this.cars=new LinkedHashSet<>();
        this.entries=new LinkedHashSet<>();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
