package org.softuni.mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
@Entity(name = "cars")
public class Car extends BaseEntity{
    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "model",nullable = false)
    private String model;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "year_of_production",nullable = false)
    private Integer yearOfProduction;

    @Column(name = "max_speed")
    private Double maxSpeed;

    @Column(name = "zero_to_sixty")
    private Double zeroToSixty;

    @ManyToOne
    @JoinColumn(name = "racer_id",referencedColumnName = "id")
    private Racer driver;

    public Car() {
    }

    public Car(String brand, String model, BigDecimal price, Integer yearOfProduction, Double maxSpeed, Double zeroToSixty, Racer driver) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.maxSpeed = maxSpeed;
        this.zeroToSixty = zeroToSixty;
        this.driver = driver;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getDriver() {
        return driver;
    }

    public void setDriver(Racer driver) {
        this.driver = driver;
    }
}
