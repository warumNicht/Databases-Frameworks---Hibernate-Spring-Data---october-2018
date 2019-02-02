package pb6_FootballBettingDatabase.entities.users;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    @Id
    @Enumerated(value = EnumType.ORDINAL)
    private Prediction id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "prediction")
    private Prediction prediction;

    @OneToMany(mappedBy = "prediction",targetEntity = BetGame.class,cascade = CascadeType.ALL)
    private Set<BetGame> betGames;

    public ResultPrediction() {
    }

    public ResultPrediction(Prediction prediction) {
        this.id = prediction;
        this.prediction = prediction;
        this.betGames=new HashSet<>();
    }

    public Prediction getId() {
        return id;
    }

    public void setId(Prediction id) {
        this.id = id;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }
}
