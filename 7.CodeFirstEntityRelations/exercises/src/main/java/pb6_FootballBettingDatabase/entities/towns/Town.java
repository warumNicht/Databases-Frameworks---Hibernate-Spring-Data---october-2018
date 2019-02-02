package pb6_FootballBettingDatabase.entities.towns;

import pb6_FootballBettingDatabase.entities.teams.Team;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Country.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "town",targetEntity = Team.class,cascade = CascadeType.ALL)
    private Set<Team> teams;

    public Town() {
        this.teams=new HashSet<>();
    }

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
        this.teams=new HashSet<>();
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
