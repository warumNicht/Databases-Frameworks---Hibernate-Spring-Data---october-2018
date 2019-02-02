package pb5_BillsPaymentSystem;


import pb5_BillsPaymentSystem.entities.User;
import pb5_BillsPaymentSystem.entities.details.BankAccount;
import pb5_BillsPaymentSystem.entities.details.BillingDetail;
import pb5_BillsPaymentSystem.entities.details.CreditCard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("bill_payment");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        User user=new User("Ivan","Ivanov","van4o@54","we345i");
        User user2=new User("Joro","Slona","jorkata@qad","8888");

        BillingDetail detail=new CreditCard("3400XXuu",user,"Debit","Mart",2019);
        BillingDetail account=new BankAccount("QU$088r",user,"fibank","BNBSOBG");
        user.getBillingDetails().add(detail);
        user.getBillingDetails().add(account);
        manager.persist(user);

        BillingDetail account2=new BankAccount("hoho",user2,"UniCredit","BNBuniG");
        user2.getBillingDetails().add(account2);
        manager.persist(user2);


        manager.getTransaction().commit();
        manager.close();
    }
}
