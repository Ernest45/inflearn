package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    @DisplayName("stateful을 유지 하는 지 ? 상태성을 유지하는 지에 대한 테스트")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : (고객 요청에서)A 사용자가 10,000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadBPrice : (고객 요청에서)B 사용자가 20,000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자 A가 주문 금액을 조회
//        int price = statefulService1.getPrice(); // 예전 코드
        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }


    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}