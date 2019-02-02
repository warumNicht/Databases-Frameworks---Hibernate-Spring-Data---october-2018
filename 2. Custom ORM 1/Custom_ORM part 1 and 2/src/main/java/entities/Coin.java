package entities;

import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

@Entity(name = "coins")
public class Coin {
    @Id
    @Column(name = "coin_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "metal")
    private String metal;
    @Column(name = "weight")
    private double weight;
    @Column(name = "durchmesser")
    private double durchmesser;

    public Coin() {
    }


    public Coin(String name, String metal, double weight, double durchmesser) {
        this.name = name;
        this.metal = metal;
        this.weight = weight;
        this.durchmesser = durchmesser;
    }
}
