import entities.BasicLabel;
import entities.ingredients.AmmoniumChloride;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FreshNuke;
import entities.shampoos.PinkPanther;
import interfaces.Shampoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo",
                "Contains mint and nettle");

        Shampoo shampoo = new FreshNuke(label);

        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(am);
        shampoo.getIngredients().add(nettle);


//        manager.persist(nettle);
//        manager.persist(mint);
//        manager.persist(label);
        manager.persist(shampoo);

        BasicShampoo secShampoo=new PinkPanther(new BasicLabel("Deutche Sauberkeit", "Absolut"));

        secShampoo.getIngredients().add(mint);
        manager.persist(secShampoo);

        manager.getTransaction().commit();
        manager.close();
    }
}
