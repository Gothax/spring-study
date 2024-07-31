package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {

        Member member = new Member("hello", 20);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);

    }

    @Test
    void findById() {
        Member member = new Member("hello", 20);
        Member member2 = new Member("hello2", 21);
        Member savedMember = memberRepository.save(member);
        Member savedMember2 = memberRepository.save(member2);

        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member = new Member("hello", 20);
        Member member2 = new Member("hello2", 21);
        Member savedMember = memberRepository.save(member);
        Member savedMember2 = memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member, member2);
    }

    @Test
    void clearStore() {
    }
}