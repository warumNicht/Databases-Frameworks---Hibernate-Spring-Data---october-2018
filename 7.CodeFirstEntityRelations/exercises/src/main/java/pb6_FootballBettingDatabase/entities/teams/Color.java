package pb6_FootballBettingDatabase.entities.teams;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "primaryColor",targetEntity = Team.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Team> teams;

    @Column(name = "name")
    private String name;

    public Color() {
        this.teams=new HashSet<>();
    }

    public Color(String name) {
        this.name = name;
        this.teams=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
