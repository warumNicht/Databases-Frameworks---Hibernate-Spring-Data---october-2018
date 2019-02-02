package pb3_University;

import pb3_University.entities.Course;
import pb3_University.entities.Student;
import pb3_University.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("university");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();

        Teacher teacher=new Teacher("Baj Stoen","Ivanov","02 03 04","baj_baj@stoen.fr",12.23);
        Teacher teacher2=new Teacher("Qnko","Kubadinski","+0034 / 06 23 456","kultura@pravets.bg",9.55);

        Course course=new Course("Deutsch","intensiv",new Date(),new Date(),12,teacher);
        Course course2=new Course("Francais","haut niveau",new Date(),new Date(),12,teacher2);
        Student student=new Student("Jovan4o","Jotata","123ddy6777",12.73,23);
        Student student2=new Student("Joro","Slona","5050",15.73,4);



        course.getStudents().add(student);
        course.getStudents().add(student2);

        course2.getStudents().add(student);

        manager.persist(course);
        manager.persist(course2);



        manager.getTransaction().commit();
        manager.close();
    }
}
