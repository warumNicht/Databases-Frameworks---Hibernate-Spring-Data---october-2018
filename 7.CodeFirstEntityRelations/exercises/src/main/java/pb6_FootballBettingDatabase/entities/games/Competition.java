package pb6_FootballBettingDatabase.entities.games;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competition_type_id",referencedColumnName = "id")
    private CompetitionType competitionType;

    @OneToMany(mappedBy = "competition",targetEntity = Game.class,cascade = CascadeType.ALL)
    private Set<Game> games;

    public Competition() {
        this.games=new HashSet<>();
    }

    public Competition(String name, CompetitionType competitionType) {
        this.name = name;
        this.competitionType = competitionType;
        this.games=new HashSet<>();
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

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
