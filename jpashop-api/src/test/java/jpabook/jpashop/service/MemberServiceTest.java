package jpabook.jpashop.service;



import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        // when
        Long saveId = memberService.join(member);
        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @DisplayName("중복테스트")
    @Test(expected = IllegalStateException.class)
    public void 중복테스트() {
        // given
        Member member = new Member();
        member.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        // when
        memberService.join(member);
        memberService.join(member2);

        // then
        Assert.fail("예외가 발생해야 한다");
    }


}