package hello;

import hello.project.domain.Member;
import hello.project.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team teamA = new Team("teamA");
            em.persist(teamA);

            Member member1 = new Member("member1");
            em.persist(member1);

            Member member2 = new Member("member2");
            em.persist(member2);

            teamA.addMembers(member1);
            teamA.addMembers(member2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
