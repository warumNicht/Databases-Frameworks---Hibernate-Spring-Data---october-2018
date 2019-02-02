package org.softuni.mostwanted.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "races")
public class Race extends BaseEntity{
    @Column(name = "laps",columnDefinition = "INT(11) DEFAULT 0")
    private Integer laps;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;


    @OneToMany(mappedBy = "race")
    private Set<RaceEntry> entries;

    public Race() {
    }

    public Race(Integer laps, District district) {
        this.laps = laps;
        this.district = district;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
