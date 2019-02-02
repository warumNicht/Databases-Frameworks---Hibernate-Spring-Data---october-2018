package pb4_Hospital;

import pb4_Hospital.core.JPManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("hospital");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Runnable system=new JPManager(manager,scanner);

        system.run();

        manager.getTransaction().commit();
        manager.close();
    }
}
