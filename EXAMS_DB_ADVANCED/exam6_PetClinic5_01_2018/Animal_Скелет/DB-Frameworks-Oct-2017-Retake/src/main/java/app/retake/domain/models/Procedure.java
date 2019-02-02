package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure extends BaseEntity{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "procedures_animal_aids",
    joinColumns = @JoinColumn(name = "procedure_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "animal_aid_id",referencedColumnName = "id"))
    private Set<AnimalAid> services;

    @ManyToOne
    @JoinColumn(name = "animal_id",referencedColumnName = "id")
    private Animal animal;
    @Transient
    private BigDecimal cost;

    @OneToOne
    @JoinColumn(name = "vet_id",referencedColumnName = "id")
    private Vet vet;

    @Column
    private Date date;

    public Set<AnimalAid> getServices() {
        return services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
