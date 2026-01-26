package io.member;

import java.util.List;

public interface MemberRepository {
    void saveMember(Member member);
    List<Member> findAll();
}
