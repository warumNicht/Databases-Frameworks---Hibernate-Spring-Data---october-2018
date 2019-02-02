package pb6_FootballBettingDatabase.entities.teams;
import org.hibernate.annotations.NaturalIdCache;
import pb6_FootballBettingDatabase.entities.games.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "players")
@NaturalIdCache
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "squad_number")
    private int squadNumber;

    @ManyToOne(targetEntity = Team.class,cascade = CascadeType.ALL)
    private Team team;

    @ManyToOne( targetEntity = Position.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Position position;

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PlayerStatistics> games;

    @Column(name = "is_injured")
    private boolean isInjured;

    public Player() {
        this.games=new HashSet<>();
    }

    public Player(String name, int squadNumber, Team team, Position position, boolean isInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.team = team;
        this.position = position;
        this.isInjured = isInjured;
        this.games=this.fillGames();
    }

    private Set<PlayerStatistics> fillGames() {
        Set<PlayerStatistics> res=new HashSet<>();

        for (Game game : this.team.getGames()) {
            PlayerStatistics current=new PlayerStatistics(game,this);
            res.add(current);
        }

        return res;
    }


    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null||this.getClass()!=obj.getClass()){
            return false;
        }
        Player player= (Player) obj;

        return Objects.equals(this.id,player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,squadNumber,team,position,
                games,isInjured);
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

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isInjured() {
        return isInjured;
    }

    public void setInjured(boolean injured) {
        isInjured = injured;
    }

    public Set<PlayerStatistics> getGames() {
        return games;
    }

    public void setGames(Set<PlayerStatistics> games) {
        this.games = games;
    }
}
