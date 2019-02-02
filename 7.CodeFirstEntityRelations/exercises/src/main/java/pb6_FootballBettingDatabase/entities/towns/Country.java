package pb6_FootballBettingDatabase.entities.towns;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Continent.class,cascade = CascadeType.ALL)
    @JoinTable(name = "countries_continents",
    joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "continent_id",referencedColumnName = "id"))
    private Continent continent;

    @OneToMany(mappedBy = "country",targetEntity = Town.class,cascade = CascadeType.ALL)
    private Set<Town> towns;

    public Country() {
        this.towns=new HashSet<>();
    }

    public Country( String name, Continent continent) {
        this.name = name;
        this.continent = continent;
        this.towns=new HashSet<>();
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

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
