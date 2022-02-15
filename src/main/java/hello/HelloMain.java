package hello;

import hello.user.domain.Address;
import hello.user.domain.HelloUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HelloMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            HelloUser helloUser = new HelloUser();
            helloUser.setUsername("helloUser");
            helloUser.setHomeAddress(new Address("homecity", "street1", "1234"));

            helloUser.getFavoriteFoods().add("치킨");
            helloUser.getFavoriteFoods().add("족발");
            helloUser.getFavoriteFoods().add("피자");

            helloUser.getAddressHistories().add(new Address("old1", "street1", "1234"));
            helloUser.getAddressHistories().add(new Address("old2", "street1", "1234"));

            em.persist(helloUser);

            em.flush();
            em.clear();

            System.out.println("=============");
            HelloUser findUser = em.find(HelloUser.class, helloUser.getId());
            findUser.setHomeAddress(new Address("newCity", "newStreet", "newZipcode"));

            findUser.getFavoriteFoods().remove("피자");
            findUser.getFavoriteFoods().add("닭발");


            System.out.println("=============");
            findUser.getAddressHistories().remove(new Address("old1", "street1", "1234"));
            findUser.getAddressHistories().add(new Address("newCity1", "street1", "1234"));

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
