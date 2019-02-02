package pb6_FootballBettingDatabase.entities.teams;

import pb6_FootballBettingDatabase.entities.games.Game;
import pb6_FootballBettingDatabase.entities.towns.Town;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logo;

    @Column(name = "initials",length = 3)
    private String initials;

    @ManyToOne( targetEntity = Color.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_color",referencedColumnName = "id")
    private Color primaryColor;

    @ManyToOne(targetEntity = Color.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_color",referencedColumnName = "id")
    private Color secondaryColor;

    @ManyToOne( targetEntity = Town.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id",referencedColumnName = "id")
    private Town town;

    @Column(name = "budget")
    private double budget;

    @OneToMany(mappedBy = "team",targetEntity = Player.class,cascade = CascadeType.ALL)
    private Set<Player> players;

    @OneToMany(mappedBy = "homeTeam",targetEntity = Game.class,cascade = CascadeType.ALL)
    private Set<Game> games;

    public Team() {
        this.players=new HashSet<>();
        this.games=new HashSet<>();
    }

    public Team(String name, String logo, String initials, Color primaryColor, Color secondaryColor, Town town, double budget) {
        this.name = name;
        this.logo = logo;
        this.initials = initials;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.town = town;
        this.budget = budget;
        this.players=new HashSet<>();
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
