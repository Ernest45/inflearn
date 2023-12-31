package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 타입은 클래스 레벨이 붙는 느낌
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
