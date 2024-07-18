package jpql;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUserName("jpa1");
            memberA.setAge(19);
            memberA.setTeam(teamA);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setUserName("jpa2");
            memberB.setAge(20);
            memberB.setTeam(teamB);
            em.persist(memberB);

            Member memberC = new Member();
            memberC.setUserName("jpa3");
            memberC.setAge(30);
            memberC.setTeam(teamB);
            em.persist(memberC);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age=20")
                    .executeUpdate();

            System.out.println("resultCount = " + resultCount);

//            //language=HQL
//            String query = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUserName() + " team : " + member.getTeam().getName());
//            }

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
