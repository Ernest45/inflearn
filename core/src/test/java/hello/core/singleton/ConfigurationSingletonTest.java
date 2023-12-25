package hello.core.singleton;

import hello.core.AppConfigV2;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("싱글톤 패턴이 깨질까?")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);


        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        // 구체 타입으로 꺼내고 있는데, 테스트 용도를 Imp에서 만들어놔서 그럼 (이러면 안 좋다)

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        // 3

        // 자바 코드인데 똑같다.
        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);


    }

    @Test
    @DisplayName("@Configuration의 마법")
    void    ConfigurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);
        //AppConfigV2.class를 넘기면 이것도 Bean에 등록됨
        AppConfigV2 bean = ac.getBean(AppConfigV2.class);

        System.out.println("bean = " + bean.getClass());
        // Class타입이 뭔지 보는 방법
    }
}
