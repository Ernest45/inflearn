package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 바뀐 정책으로 서비스 영역에서 바꿔야 한다는 문제점이 있다. 1. 그래서 밑에 코드로 변경함!

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //  2. 이대로 바꾸면 구현체가 아닌 인터페이스기에 discountPolicy에 구체 값이 없기에 NPE에러가 남.
    //  3. 그래서 따로 생성자 만들어주고, appconfig를 통해 생성자를 가져다 쓰면 DIP 지키게 됨 위에 보면 인터페이스만 의존


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