package org.softuni.mostwanted.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District extends BaseEntityWithName {

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    public District() {
    }

    public District(String name) {
        super(name);
    }


    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
