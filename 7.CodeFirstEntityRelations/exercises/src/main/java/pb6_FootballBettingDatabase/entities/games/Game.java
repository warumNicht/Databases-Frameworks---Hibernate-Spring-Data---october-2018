package pb6_FootballBettingDatabase.entities.games;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import pb6_FootballBettingDatabase.entities.teams.PlayerStatistics;
import pb6_FootballBettingDatabase.entities.teams.Player;
import pb6_FootballBettingDatabase.entities.teams.Team;
import pb6_FootballBettingDatabase.entities.users.Bet;
import pb6_FootballBettingDatabase.entities.users.BetGame;
import pb6_FootballBettingDatabase.entities.users.ResultPrediction;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id",referencedColumnName = "id")
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id",referencedColumnName = "id")
    private Team awayTeam;

    @Column(name = "home_goals")
    private int homeGoals;

    @Column(name = "away_goals")
    private int awayGoals;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "home_bet_win_rate")
    private int homeBetWinRate;

    @Column(name = "away_bet_win_rate")
    private int awayBetWinRate;

    @Column(name = "draw_bet_win_rate")
    private int drawBetWinRate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id",referencedColumnName = "id")
    private Round round;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competition_id",referencedColumnName = "id")
    private Competition competition;

    @OneToMany(mappedBy = "player",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PlayerStatistics> players;

    @OneToMany(mappedBy = "game",targetEntity = BetGame.class)
    private Set<BetGame> betGames;


    public Game() {
        this.players=new HashSet<>();
        this.betGames=new HashSet<>();
    }

    public Game(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals, String startDate,String endDate,
                Round round,Competition competition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.startDate = this.parseDate(startDate);
        this.endDate=this.parseDate(endDate);
        this.round=round;
        this.competition=competition;
        this.players=this.fillPlayers();
        this.betGames=new HashSet<>();
    }

    private Date parseDate(String date) {
        SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date res=format.parse(date);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBetGameToGame(Bet bet, ResultPrediction resultPrediction){
        BetGame betGame=new BetGame(bet,this,resultPrediction);
        this.betGames.add(betGame);
        this.dispatchResultPrediction(betGame);

    }

    private void dispatchResultPrediction(BetGame betGame) {
        String resPrediction=betGame.getPrediction().getPrediction().name();
        switch (resPrediction){
            case "HOME":{
                ++this.homeBetWinRate;
            }break;
            case "AWAY":{
                ++this.awayBetWinRate;
            }break;
            case "DRAW":{
                ++this.drawBetWinRate;
            }break;
        }
    }

    private Set<PlayerStatistics> fillPlayers() {
        Set<PlayerStatistics> res=new HashSet<>();
        Game game=this;
        for (Player player : homeTeam.getPlayers()) {
            PlayerStatistics current=new PlayerStatistics(game,player);
            res.add(current);
        }
        for (Player player : awayTeam.getPlayers()) {
            PlayerStatistics current=new PlayerStatistics(game,player);
            res.add(current);
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game that= (Game) o;
        return Objects.equals(this.id,that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,homeTeam,awayTeam,homeGoals,awayGoals,
                startDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Set<PlayerStatistics> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerStatistics> players) {
        this.players = players;
    }

    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getHomeBetWinRate() {
        return homeBetWinRate;
    }

    public void setHomeBetWinRate(int homeBetWinRate) {
        this.homeBetWinRate = homeBetWinRate;
    }

    public int getAwayBetWinRate() {
        return awayBetWinRate;
    }

    public void setAwayBetWinRate(int awayBetWinRate) {
        this.awayBetWinRate = awayBetWinRate;
    }

    public int getDrawBetWinRate() {
        return drawBetWinRate;
    }

    public void setDrawBetWinRate(int drawBetWinRate) {
        this.drawBetWinRate = drawBetWinRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
