package pb1_GringottsDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("gringotts");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        WizardDeposit deposit=new WizardDeposit("Gandalf","The Gray","excellent",280,
                "Enth Creator",(short) 23,"WIZZ",new Date(),200.5,
                0.1,0.02,new Date());

        manager.persist(deposit);

        WizardDeposit deposit2=new WizardDeposit("Radagast","The Brown","interesting",190,
                "Nature",(short) 12,"LLM",new Date(),130.5,
                0.1,0.02,new Date());
        manager.persist(deposit2);
        deposit2.setDepositExpired(true);


        manager.getTransaction().commit();
        manager.close();
    }
}
