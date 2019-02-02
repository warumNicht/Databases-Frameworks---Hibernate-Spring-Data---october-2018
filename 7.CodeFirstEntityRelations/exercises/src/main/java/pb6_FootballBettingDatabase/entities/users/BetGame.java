package pb6_FootballBettingDatabase.entities.users;

import pb6_FootballBettingDatabase.entities.games.Game;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {

    @Id
    @ManyToOne(targetEntity = Bet.class)
    @JoinColumn(name = "bet_id",referencedColumnName = "id")
    private Bet bet;

    @Id
    @ManyToOne (targetEntity = Game.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prediction_id",referencedColumnName = "id")
    private ResultPrediction prediction;

    public BetGame() {
    }

    public BetGame(Bet bet, Game game, ResultPrediction prediction) {
        this.bet = bet;
        this.game = game;
        this.prediction = prediction;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getPrediction() {
        return prediction;
    }

    public void setPrediction(ResultPrediction prediction) {
        this.prediction = prediction;
    }
}
