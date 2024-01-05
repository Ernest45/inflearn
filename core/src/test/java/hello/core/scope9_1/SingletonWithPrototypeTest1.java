package hello.core.scope9_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeTest() {

        GenericApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("clientA로 테스트 : 1이 나오길 2가 찍힘")
    void singletonClientUesPrototypeTestA() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanA.class, PrototypeBean.class);

        ClientBeanA clientBean1 = ac.getBean(ClientBeanA.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanA clientBean2 = ac.getBean(ClientBeanA.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @Test
    @DisplayName("ClientB로 테스트 : A테스트를 해결하기위해 1차적인 해결법")
    void singletonClientUesPrototypeTestbB() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanB.class, PrototypeBean.class);

        ClientBeanB clientBean1 = ac.getBean(ClientBeanB.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanB clientBean2 = ac.getBean(ClientBeanB.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("ClientC로 테스트 : A테스트와 B를 깔끔하게 해결하기 위해 Provider 사용하기")
    void singletonClientUesPrototypeTestC() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanC.class, PrototypeBean.class);

        ClientBeanC clientBean1 = ac.getBean(ClientBeanC.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanC clientBean2 = ac.getBean(ClientBeanC.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("ClientD로 테스트 : Provider사용하는데, 자바 순수 코드로 사용해보기")
    void singletonClientUesPrototypeTestD() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBeanD.class, PrototypeBean.class);

        ClientBeanD clientBean1 = ac.getBean(ClientBeanD.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanD clientBean2 = ac.getBean(ClientBeanD.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBeanB {
//        private final PrototypeBean prototypeBean; // 생성 시점에 주입

        //        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
        @Autowired
        ApplicationContext applicationContext;

        public int logic() {
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBeanA { // 클라이언트Bean 테스트
        private final PrototypeBean prototypeBean; // 생성 시점에 주입

       @Autowired
        public ClientBeanA(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }}

        static class ClientBeanC { // Provider로 싱글톤 + 프로토타입 구현

            @Autowired
            private ObjectProvider<PrototypeBean> prototypeBeanProvider;


            public int logic() {
                PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
                prototypeBean.addCount();
                int count = prototypeBean.getCount();
                return count;
            }}

            static class ClientBeanD { // 자바 순수 Provider 라이브러리 사용 (스프링 의존 뺴기)

                @Autowired
                private Provider<PrototypeBean> prototypeBeanProvider;


                public int logic() {
                    PrototypeBean prototypeBean = prototypeBeanProvider.get();
                    prototypeBean.addCount();
                    int count = prototypeBean.getCount();
                    return count;
                }
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this); // this를 찍으면 현재 참조 값 찍는 것
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.close");
        }
    }
}
