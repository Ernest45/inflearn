package hello.core.beanfind;

import hello.core.AppConfigV2;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService); // 이건 인스턴스 주소까지 나온다!
        System.out.println("memberService.getClass() = " + memberService.getClass()); //getClass는 타입만 가져옴

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //스테틱 임폴트 사용
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); //위 처럼 이름 없이 타입으로만 조회 가능!
        // but! (같은 타입이 여러 개 일 경우 곤란)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 인터페이스 타입말고, 구체적인 구현체인 Impl 타입을 적어줘도 된다! but 안좋다 (유연성이 떨어짐)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNamex() {
//        MemberService memberService = ac.getBean("xxxx", MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); // 람다를 사용해서 저 예외가 터져야한다는 뜻!
    }


}
