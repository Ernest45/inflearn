package hello.core.beandefiniton;

import hello.core.AppConfigV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    // BeanDefinition이라는 걸로 스프링 빈 설정 메타 정보를 추상화한다!.

    //xml과 appconfig의 차이는 xml은 직접 스프링 빈을 컨테이너에 등록하는 방법이고,
    //java 코드로 등록하는 건 appConfig가 제공하는 메서드를 사용해 우회 방법인 팩토리 메서드 방법이다.
    // 즉 appconfig 자체가 팩토리빈이고, 그안에 메서드들이 팩토리 메서드들 (memberSer,Repo, 등등

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);

//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    // ApplicationContext 타입으로(상위타입이긴 하지만)는 .getBeanDefinition 못쓴다 그 이유는 위에 인터페이스가 더 있어서

    @Test
    @DisplayName("BeanDefinition이 어노테이션 or xml로 읽어와서 설정 메타 정보를 등록 및 반환")
    void beanDefinitionTestAboutAnnotation() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                "beanDefinition = " + beanDefinition);
            }
        }

    }
}
