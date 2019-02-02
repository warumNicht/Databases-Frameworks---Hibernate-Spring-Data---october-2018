package org.softuni.mostwanted.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "towns")
public class Town extends BaseEntityWithName {

    @OneToMany(mappedBy = "town")
    private Set<District> districts;

    @OneToMany(mappedBy = "homeTown")
    private Set<Racer> racers;

    public Town() {
        this.districts=new LinkedHashSet<>();
        this.racers=new LinkedHashSet<>();
    }

    public Town(String name) {
        super(name);
        this.districts=new LinkedHashSet<>();
        this.racers=new LinkedHashSet<>();
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public Set<Racer> getRacers() {
        return racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        Town that = (Town) obj;
        return this.getName().equals(that.getName());
    }
}
