package org.softuni.mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityWithName extends BaseEntity{
    @Column(name = "name",nullable = false)
    private String name;

    public BaseEntityWithName() {
    }

    public BaseEntityWithName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
