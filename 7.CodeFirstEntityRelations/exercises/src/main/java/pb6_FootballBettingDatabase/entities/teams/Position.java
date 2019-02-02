package pb6_FootballBettingDatabase.entities.teams;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "position",targetEntity = Player.class,cascade = CascadeType.ALL)
    private Set<Player> players;

    public Position() {
        this.players=new HashSet<>();
    }

    public Position(String description) {
        this.description = description;
        this.players=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
