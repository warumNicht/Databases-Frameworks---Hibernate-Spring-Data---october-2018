package pb6_FootballBettingDatabase;

import pb6_FootballBettingDatabase.entities.games.Competition;
import pb6_FootballBettingDatabase.entities.games.CompetitionType;
import pb6_FootballBettingDatabase.entities.games.Game;

import pb6_FootballBettingDatabase.entities.games.Round;
import pb6_FootballBettingDatabase.entities.teams.Color;
import pb6_FootballBettingDatabase.entities.teams.Player;
import pb6_FootballBettingDatabase.entities.teams.Position;
import pb6_FootballBettingDatabase.entities.teams.Team;
import pb6_FootballBettingDatabase.entities.towns.Continent;
import pb6_FootballBettingDatabase.entities.towns.Country;
import pb6_FootballBettingDatabase.entities.towns.Town;
import pb6_FootballBettingDatabase.entities.users.Bet;
import pb6_FootballBettingDatabase.entities.users.Prediction;
import pb6_FootballBettingDatabase.entities.users.ResultPrediction;
import pb6_FootballBettingDatabase.entities.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("bookmaker_system");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Continent europe = new Continent("Europe");
        Continent africa = new Continent("Africa");

        Country france = new Country("France", europe);
        Country espana = new Country("Reino de España", europe);
        Country madagascar=new Country("Madagascar",africa);

        Town paris = new Town("Paris", france);
        Town barsa= new Town("Barcelona",espana);
        Town antananarivo=new Town("Antananarivo",madagascar);

        france.getTowns().add(paris);
        espana.getTowns().add(barsa);
        madagascar.getTowns().add(antananarivo);

        europe.getCountries().add(france);
        europe.getCountries().add(espana);
        africa.getCountries().add(madagascar);

        manager.persist(europe);

        Color black = new Color("black");
        Color red = new Color("red");
        Color blue = new Color("blue");
        Color yellow = new Color("yellow");

        Position napadatel = new Position("napadatel");
        Position otbrana = new Position("defenser");
        Position vratar = new Position("vratar");

        Team levski = new Team("Levski", "llllW", "LEV", yellow, blue, paris, 23.74);
        Team csk = new Team("CSK", "3233W", "CSK", black, red, paris, -3.04);
        Team barca=new Team("Barca","Barcelonense","BA",yellow,red,barsa,35000.56);
        Team tumboLumbo=new Team("Tumbo - Lumbo", "Jungle","TLM",red,blue,antananarivo,5.24);

        Player iceto = new Player("Icito majna", 8, levski, napadatel, false);
        Player gonzo = new Player("Gonzo", 5, levski, napadatel, false);
        Player messi = new Player("Mesitu", 22, barca, napadatel, true);
        Player stoyno = new Player("Stojnata", 12, csk, vratar, false);
        Player bate = new Player("Bateto", 1, csk, otbrana, true);
        Player toto = new Player("Tooto", 15, tumboLumbo, otbrana, false);


        napadatel.getPlayers().add(iceto);
        napadatel.getPlayers().add(gonzo);
        napadatel.getPlayers().add(messi);

        otbrana.getPlayers().add(bate);
        otbrana.getPlayers().add(toto);

        vratar.getPlayers().add(stoyno);

        levski.getPlayers().add(iceto); levski.getPlayers().add(gonzo);
        csk.getPlayers().add(stoyno); csk.getPlayers().add(bate);
        barca.getPlayers().add(messi);
        tumboLumbo.getPlayers().add(toto);

        CompetitionType type=new CompetitionType("Fifa");
        CompetitionType type2=new CompetitionType("Word");
        Competition competition1=new Competition("Cup Europe",type);
        Competition competition2=new Competition("Mondial Rio",type2);

       manager.persist(competition1);
       manager.persist(competition2);

        Round round=new Round("polufinal");
        Round roundFinal=new Round("FINAL");

        Game cskLevski = new Game(csk, levski, 3, 5, "12-05-1985 17:50", "12-05-1985 19:52",
                round,competition1);
        Game levskiCsk = new Game( levski,csk, 2, 4, "12-05-1994 21:00", "12-05-1994 22:30",
                round,competition1);
        Game barcaLevski = new Game( barca,levski, 3, 0, "12-05-1998 21:30", "12-05-1998 22:38",
                roundFinal,competition2);
        Game tumboCsk = new Game( tumboLumbo,csk, 6, 0, "12-04-1998 21:22", "12-04-1998 22:58",
                roundFinal,competition2);

        manager.persist(cskLevski);
        manager.persist(levskiCsk);
        manager.persist(barcaLevski);
        manager.persist(tumboCsk);

        User user = new User("walkata", "555%%#2@", "cepo_majmunkata@bnb.com", "Walter Gropius", 45.21);
        Bet bet = new Bet(65.90, new Date(), user);
        ResultPrediction predictionAway = new ResultPrediction(Prediction.AWAY);
        ResultPrediction predictionDraw = new ResultPrediction(Prediction.DRAW);
        ResultPrediction predictionHome = new ResultPrediction(Prediction.HOME);

        bet.addBetGameToBet(cskLevski, predictionAway);
        cskLevski.addBetGameToGame(bet, predictionAway);

        bet.addBetGameToBet(levskiCsk,predictionDraw);
        levskiCsk.addBetGameToGame(bet,predictionDraw);

        bet.addBetGameToBet(barcaLevski,predictionHome);
        barcaLevski.addBetGameToGame(bet,predictionHome);

        bet.addBetGameToBet(tumboCsk,predictionHome);
        tumboCsk.addBetGameToGame(bet,predictionHome);

        manager.persist(bet);

        User user2 = new User("pepo", "er^9)@@", "pepo_gorskiq@sad.gb", "Petyr Stoqnovi4", 5.25);
        Bet bet2 = new Bet(150.66, new Date(), user2);
        bet2.addBetGameToBet(tumboCsk,predictionHome);
        tumboCsk.addBetGameToGame(bet2,predictionHome);

        manager.persist(bet2);

        manager.getTransaction().commit();
        manager.close();
    }
}
