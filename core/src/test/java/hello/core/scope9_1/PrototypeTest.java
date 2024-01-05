package hello.core.scope9_1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        GenericApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("PrototypeTest.prototypeBeanFind1"); // 프로토타입을 조회하기 직전에 생성되기에 호출해보기
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeTest.prototypeBeanFind2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        prototypeBean1.close();
        prototypeBean2.close();

        ac.close();


    }
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }


    }

}
