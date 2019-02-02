package pb6_FootballBettingDatabase.entities.games;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "competitions_types")
public class CompetitionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "competitionType",targetEntity = Competition.class,cascade = CascadeType.ALL)
    private Set<Competition> competitions;

    public CompetitionType() {
        this.competitions=new HashSet<>();
    }

    public CompetitionType(String type) {
        this.type = type;
        this.competitions=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}
