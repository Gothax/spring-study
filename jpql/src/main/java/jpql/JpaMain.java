package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUserName("jpa1");
            member.setAge(19);
            em.persist(member);

            em.flush();
            em.clear();

            List<Team> resultList = em.createQuery("select t from Member as m join m.team as t", Team.class)
                    .getResultList();

            List<Team> resultList2 = em.createQuery("select t from Member as m join m.team as t", Team.class)
                    .getResultList();

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
