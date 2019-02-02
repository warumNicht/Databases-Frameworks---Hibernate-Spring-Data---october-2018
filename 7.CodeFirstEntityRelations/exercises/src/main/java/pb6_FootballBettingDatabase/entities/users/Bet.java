package pb6_FootballBettingDatabase.entities.users;

import pb6_FootballBettingDatabase.entities.games.Game;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "money")
    private double money;

    @Column(name = "date")
    private Date date;

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "bet",targetEntity = BetGame.class,cascade = CascadeType.ALL)
    private Set<BetGame> betGames;

    public Bet() {
        this.betGames=new HashSet<>();
    }

    public Bet(double money, Date date, User user) {
        this.money = money;
        this.date = date;
        this.user = user;
        this.betGames = new HashSet<>();
    }
    public void addBetGameToBet(Game game, ResultPrediction resultPrediction){
        BetGame betGame=new BetGame(this,game,resultPrediction);
        this.betGames.add(betGame);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGame) {
        this.betGames = betGame;
    }
}
