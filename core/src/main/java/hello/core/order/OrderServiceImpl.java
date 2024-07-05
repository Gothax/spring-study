package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

import java.net.DatagramSocket;

public class OrderServiceImpl implements OrderService{
//    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // OCP 위반
//    private DiscountPolicy discountPolicy;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 구현체를 바꾸기 위해 코드를 수정 -> OCP 위반

    private final MemberRepository memberRepository; // OCP 위반
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
