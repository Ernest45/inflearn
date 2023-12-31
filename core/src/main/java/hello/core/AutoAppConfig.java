package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// 자동으로 스캔해서 등록인데, 위 조건은 따로 뺄 것을 정해준다. 우리가 수동으로 등록한 AppCofigV2는 등록되면 안되기에 뺀다)
public class AutoAppConfig {



    @Bean
    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
