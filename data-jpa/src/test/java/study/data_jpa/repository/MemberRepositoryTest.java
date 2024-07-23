package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired EntityManager em;
    
    @Test
    public void testMember(){
        Member member = new Member("memberA", 19);
        Member savedMember = memberRepository.save(member);

        Member member1 = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(savedMember.getId()).isEqualTo(member1.getId());
    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("memberA", 19);
        Member member2 = new Member("memberB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        //단건 조회 검증
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회 검증
//        List<Member> all = memberRepository.findAll();
//        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(findMember1);
        memberRepository.delete(findMember2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    void findByUsernameAndAgeGreaterThan() {
        Member member = new Member("memberA", 19);
        Member member2 = new Member("memberB", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("memberA", 14);

        assertThat(result.get(0).getUsername()).isEqualTo(member.getUsername());
        assertThat(result.get(0).getAge()).isEqualTo(member.getAge());
        assertThat(result.size()).isEqualTo(1);


    }

    @Test
    void findUser() {
        Member member = new Member("memberA", 19);
        Member member2 = new Member("memberB", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> findMember = memberRepository.findUser("memberA", 19);
        assertThat(findMember.get(0).getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember.size()).isEqualTo(1);
    }

    @Test
    void findUsernameList() {
        Member member = new Member("memberA", 19);
        Member member2 = new Member("memberB", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    void findMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);
        Member member = new Member("memberA", 19);
        member.setTeam(team);
        memberRepository.save(member);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }

    }

    @Test
    void findByNames() {
        Member member = new Member("memberA", 19);
        Member member2 = new Member("memberB", 20);
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("memberA", "memberB"));
        for (Member mem : result) {
            System.out.println("mem = " + mem);
        }
    }


    @Test
    public void paging(){
        memberRepository.save(new Member("member1", 19));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 19));
        memberRepository.save(new Member("member4", 19));
        memberRepository.save(new Member("member5", 19));
        memberRepository.save(new Member("member6", 19));
        int age=19;
        int offset=0;
        int limit=3;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        // page 1 -> offset = 0 limit 10, page 2 -> offset = 10, limit 10

        Page<Member> page = memberRepository.findByAge(19, pageRequest);

        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));

        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(6);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }


//
//    @Test
//    public void slicing(){
//        memberRepository.save(new Member("member1", 19));
//        memberRepository.save(new Member("member2", 19));
//        memberRepository.save(new Member("member3", 19));
//        memberRepository.save(new Member("member4", 19));
//        memberRepository.save(new Member("member5", 19));
//        memberRepository.save(new Member("member6", 19));
//        int age=19;
//        int offset=0;
//        int limit=3;
//
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
//
//        // page 1 -> offset = 0 limit 10, page 2 -> offset = 10, limit 10
//
//        Slice<Member> page = memberRepository.findByAge(19, pageRequest);
//
//        List<Member> content = page.getContent();
//
//        assertThat(content.size()).isEqualTo(3);
//        assertThat(page.getNumber()).isEqualTo(0);
//        assertThat(page.isFirst()).isTrue();
//        assertThat(page.hasNext()).isTrue();
//    }

    @Test
    void bulkAgePlus() {
        memberRepository.save(new Member("member1", 11));
        memberRepository.save(new Member("member2", 14));
        memberRepository.save(new Member("member3", 16));
        memberRepository.save(new Member("member4", 18));
        memberRepository.save(new Member("member5", 21));
        memberRepository.save(new Member("member6", 41));

        int resultCount = memberRepository.bulkAgePlus(15);

        assertThat(resultCount).isEqualTo(4);
    }
    
    @Test
    public void findMemberLazy(){
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        Team teamB = new Team("teamB");
        teamRepository.save(teamB);
        
        Member member1 = new Member("memberA", 19);
        member1.setTeam(teamA);
        memberRepository.save(member1);
        Member member2 = new Member("memberB", 20);
        member2.setTeam(teamB);
        memberRepository.save(member2);
        
        em.flush();
        em.clear();

        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint(){
        Member member = new Member("memberA", 19);
        memberRepository.save(member);

        em.flush();
        em.clear();

//        Member findMember = memberRepository.findById(member.getId()).get();

        Member findMember = memberRepository.findReadOnlyByUsername("memberA");
        findMember.setUsername("member2");

        em.flush();
    }

    @Test
    public void callCustom(){
        List<Member> memberCustom = memberRepository.findMemberCustom();
    }
}