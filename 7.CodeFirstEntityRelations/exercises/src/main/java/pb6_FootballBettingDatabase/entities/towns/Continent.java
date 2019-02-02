package pb6_FootballBettingDatabase.entities.towns;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "continent",targetEntity = Country.class,cascade = CascadeType.ALL)
    private Set<Country> countries;

    public Continent() {
        this.countries=new HashSet<>();
    }

    public Continent(String name) {
        this.name = name;
        this.countries=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
