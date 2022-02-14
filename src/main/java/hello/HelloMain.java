package hello;

import hello.project.domain.Child;
import hello.project.domain.Member;
import hello.project.domain.Parent;
import hello.project.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HelloMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Parent parentA = new Parent("parent A");
            Parent parentB = new Parent("parent B");

            em.persist(parentA);
            em.persist(parentB);

            Child child1 = new Child("child 1");
            Child child2 = new Child("child 2");

            child1.setParent(parentA);
            parentB.addChildren(child2);

            em.flush();
            em.clear();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
