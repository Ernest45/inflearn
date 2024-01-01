package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigV2 {
/**
    @Bean memberSerivce를 호출하면 결국 -> new MemoryMemberRepository()
    @Bean orderSerivce를 호출하면 또-> new MemoryMemberRepository()
    여기서 싱글톤이 깨질까 ? 안깨질까 ? 각 각 두개가 생성되는 걸로 new를 하는데도 ?
     테스트로 memberServiceImp의 memberRepo랑 order의 meberRepo를 get해서 확인해보자

     안깨진다. 그럼 호출을 안하는건가 ? 확인해보자
     순서대로 따진다면, sout 로그로 확인해보면 (순서는 보장하지 않지만 위에서부터 다 호출한다고 가정)
    1.call AppConfigV2.memberService
    2.call AppConfigV2.memberRepository
    3.call AppConfigV2.orderService
    4.call AppConfigV2.memberRepository
    5.call AppConfigV2.memberRepository

  but (이렇게만 호출됨)
    1.call AppConfigV2.memberService
    2.call AppConfigV2.memberRepository
    3.call AppConfigV2.orderService

  왜냐 있음 새로 new 하지 않고 찾아서 반환하기에 (@Configuration의 비밀)


 */


    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfigV2.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfigV2.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfigV2.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
