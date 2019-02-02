import ORM.Connector;
import ORM.DbContext;
import ORM.EntityManager;
import entities.Book;
import entities.Coin;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String username="root";
        String password="0000";
        String dBase="testORM";

        Connector.createConnection(username,password,dBase);
        DbContext manager=new EntityManager(Connector.getConnection());

        Coin coin=new Coin("Sveti Ivan","Ag 925/1000",23.33,38.61);
        manager.persist(coin);


        Book curBook= (Book) manager.findFirst(Book.class,"price>30");
        manager.persist(curBook);

        manager.doDelete(User.class,"id BETWEEN 2 AND 26");

        Iterable allBooks=manager.find(Book.class,"price>30");
        for (Object allBook : allBooks) {
            System.out.println(allBook);
        }
//
//        Book book=new Book("The Lord of the rings", "Talkien",23.67);
//        bookManager.persist(book);
//        bookManager.persist(new Book("Трима на бумел", "Джером Джером",17.54));
//        bookManager.persist(new Book("Les Miserables", "Victor Hugo",32.15));

        User currentUser=new User("Petar",55,23,"Potsdam",new Date());
        manager.persist(currentUser);
//        currentUser.setAge(100);
//        manager.persist(currentUser);
//        manager.persist(new User("Wilhelm Pick",68,new Date()));
//        manager.persist(new User("Mustafa",23,new Date()));
//        manager.persist(new User("Aj6e",42,new Date()));
//        manager.persist(new User("Koko",17,new Date()));

     User found = (User) manager.findFirst(User.class, " age>68 ");
//
//        if (found.getName() != null)
//            System.out.println(found);
//        System.out.println();

        Iterable allUsers=manager.find(User.class," age>35");

        for (Object allUser : allUsers) {
            System.out.println(allUser);
        }
    }
}
