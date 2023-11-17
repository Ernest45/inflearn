package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp { // 테스트 코드 안쓰고 테스트 해보기
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

       /** v1의 방식

        AppConfigV1 appConfigV1 = new AppConfigV1();
        MemberService memberService = appConfigV1.memberService();
        OrderService orderService = appConfigV1.orderService();
        **/

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);
        // 컨테이너 등록하면 스프링 컨테이너에서는 @Configuration 이 붙은 AppConfig의 설정(구성) 정보를 사용
        // 여기서 @Bean 이 붙은 메서드들을 모두 호출해 반환된 객체(return되었으니까)를 스프링 컨테이너에 등록함
        // 이렇게 등록된 객체를 스프링 빈이라고 한다!

        OrderService orderService = ac.getBean("orderService", OrderService.class);
        // 스프링 빈은 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.  ex) "orderService"
        // 찾는 방법은 ac.getBean("orderService" [이게 이름], OrderService.class [타입] ); 이런 형식이다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP); //멤버를 저장하고,
        memberService.join(member); //db에 넣어놓기 위해 join

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order.toString());
        System.out.println("order = " + order); // 이게 tostring을 호출 하는 것 위랑 같다.
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
