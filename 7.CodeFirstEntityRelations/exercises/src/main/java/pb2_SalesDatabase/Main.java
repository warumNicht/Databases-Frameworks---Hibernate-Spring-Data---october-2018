package pb2_SalesDatabase;


import pb2_SalesDatabase.entities.Customer;
import pb2_SalesDatabase.entities.Location;
import pb2_SalesDatabase.entities.Product;
import pb2_SalesDatabase.entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("sales");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Product kase=new Product("Kase",34.6,new BigDecimal(15.69));
        Product wurst=new Product("Wurst",12.6,new BigDecimal(25.12));
        Product product3=new Product("Shop salad",55.0,new BigDecimal(6.75));
        Product product4=new Product("Apfelsaft",13.0,new BigDecimal(5.64));

        Location sofia=new Location("Sofia");
        Location berlin=new Location("Berlin");
        Location wien=new Location("Wien");
        Location frankfurt=new Location("Frankfurt am Mein");

        manager.persist(product3);

        Customer pipeto=new Customer("Pipeto Vorobqninov","russ@ru","348fpre8RTT45H");
        Customer wilhelm=new Customer("Wilhelm Pick","Willy.Brandt@yahoo.de","OOH3459289955");
        Customer keiser=new Customer("Keiser Fridrich","bundestag@merkel.eu","NaOH#33ui78");

        Sale sale=new Sale(wurst,new Date(),wien,pipeto);
        Sale sale2=new Sale(kase,new Date(),sofia,wilhelm);

        manager.persist(sale);
        manager.persist(sale2);
        manager.persist(new Sale(product4,new Date(),frankfurt,pipeto));
        manager.persist(new Sale(wurst,new Date(),berlin,keiser));

//        Customer customer1=manager.find(Customer.class,2);
//        Location location1=manager.find(Location.class,3);
//        Product product1=manager.find(Product.class,1);

        manager.getTransaction().commit();
        manager.close();
    }
}
