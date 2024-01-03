package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {


    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientAboutMet client = ac.getBean(NetworkClientAboutMet.class);
        ac.close();
    }



    @Configuration
    static class LifeCycleConfig {

//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClientAboutMet networkClient() {
            NetworkClientAboutMet networkClient = new NetworkClientAboutMet();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
