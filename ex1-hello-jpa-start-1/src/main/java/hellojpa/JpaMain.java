package hellojpa;

import domain.Book;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            em.createNativeQuery("select MEMBER_ID, city, street, zip_code from MEMBER")
                            .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
          em.close();
        }

        emf.close();
    }
}
