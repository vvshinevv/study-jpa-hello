package hello;

import hello.project.domain.Member;
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
            Team teamA = new Team("teamA");
            em.persist(teamA);

            Team teamB = new Team("teamB");
            em.persist(teamB);

            Team teamC = new Team("teamC");
            em.persist(teamC);

            Member member1 = new Member("member1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            Member member3 = new Member("member3");
            member3.setTeam(teamC);
            em.persist(member3);

            em.flush();
            em.clear();

            List<Member> findMembers = em.createQuery("select m from Member m", Member.class).getResultList();
            // select * from Member; -> eager 라서 team 도 가져온다...

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
