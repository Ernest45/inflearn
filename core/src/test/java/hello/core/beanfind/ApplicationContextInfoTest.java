package hello.core.beanfind;

import hello.core.AppConfigV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//public class ApplicationContextInfoTest { //junit5 부터 테스트에 public 설정 안해줘도 된다!.
class ApplicationContextInfoTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigV2.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { // iter + tap 누르면 리스트 or 배열 같은 게 있으면 for문 자동완성됨
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);

        }
    }   @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // getBeanDefinition은 빈 하나 하나에 대한 메타데이터 정보를 가져오는 것


            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //
                // 롤은 내가 등록한 빈만 가져오게 하기 위한 경우다!
                // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
                // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);

        }


        }
    }
}
