package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;

// 스프링을 사용하지 않는 예제
public class AppConfigV1 { // 앱 컨피그가 외부에서 넣어주는 애 역할

    //appconfig는 무조건 변경은 해야 하는 건 맞다. (구현 계획자니까 구현 참여자를 다 알아야함)

    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
        //생성자를 통해 객체가 인스턴스 생성된 게 들어간다고 해서 "생성자 주입!" 이라고 부른다.

        //멤버서비스임플은 new해서 메모리멤버레파짓 객체를 생성해서 이거에 대한 참조 값을 부르는 생성자를 통해 넣어준다.
    }

    public OrderService orderService() {
//        return new OrderServiceImpl(MemberRepository(), discountPolicy());
        return null;
    }

    public MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
