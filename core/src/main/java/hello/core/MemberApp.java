package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp { // 테스트 코드 안쓰고 테스트 해보기
    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl();

//        AppConfigV1 appConfigV1 = new AppConfigV1(); //v1의 방식
//        MemberService memberService = appConfigV1.memberService(); //v1의 방식

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);
        //어노테이션을 기반으로 appconfig에 등록된 환경설정정보를 가지고 스프링이 스프링 컨테이너에다가 객체 생성한 것을 넣어서 관리해줌!
        MemberService memberService = ac.getBean("memberService", MemberService.class);// 메서드 이름으로 등록하고, 뒤에는 반환 타입이다!

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
