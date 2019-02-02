package pb6_FootballBettingDatabase.entities.teams;

import pb6_FootballBettingDatabase.entities.games.Game;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "players_statistics")
public class PlayerStatistics implements Serializable {
    @Id
    @ManyToOne(targetEntity = Game.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    private Game game;

    @Id
    @ManyToOne(targetEntity = Player.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    private Player player;

    @Column(name = "scored_goals")
    private int scoredGoals;

    @Column(name = "players_assist")
    private int playersAssist;

    @Column(name = "played_minutes")
    private int playedMinutes;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.scoredGoals=this.calculateGoals();
        this.playersAssist=this.calculateAssistingplayers();
        this.playedMinutes=this.calculatePlayDuration();
    }

    private int calculatePlayDuration() {
        Date start=this.game.getStartDate();
        Date end=this.game.getEndDate();
        long differenceMilliSec = end.getTime() - start.getTime();

        differenceMilliSec/=(1000*60);

        return (int) differenceMilliSec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PlayerStatistics that= (PlayerStatistics) o;
        return Objects.equals(this.game,that.game)&&
                Objects.equals(this.player,that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game,player,scoredGoals);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayersAssist() {
        return playersAssist;
    }

    public void setPlayersAssist(int playersAssist) {
        this.playersAssist = playersAssist;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }

    private int calculateAssistingplayers() {
        return this.game.getHomeTeam().getPlayers().size()+
                this.game.getAwayTeam().getPlayers().size();
    }

    private int calculateGoals() {
        String playerTeam=this.player.getTeam().getName();
        if(playerTeam.equals(this.game.getHomeTeam().getName())){
            return this.game.getHomeGoals();
        }else {
            return this.game.getAwayGoals();
        }
    }
}
