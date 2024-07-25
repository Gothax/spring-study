package study.querydsl.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {
    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {
        // given
        Team team = new Team("teamA");
        Team team2 = new Team("teamB");
        em.persist(team);
        em.persist(team2);

        Member memberA = new Member("memberA", 10, team);
        Member memberB = new Member("memberB", 20, team);
        Member memberC = new Member("memberC", 30, team2);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.flush();
        em.clear();
        // when
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
        }
        // then
        assertThat(members.size()).isEqualTo(3);
    }
}