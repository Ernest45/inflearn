package hello.core.common9_2;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL; // 나중에 별도로 세팅하기 위 중간에 들어오게 Setter 만들어주자

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL +  "] " + message);
    }

    @PostConstruct
    public void init() {
       uuid = UUID.randomUUID().toString(); // 겹칠 확률이 극악
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy // request Scope는 소멸이 됨 요청 시 init호출하고 빠져나갈 때 close 호출하고 소멸됨
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);


    }
}
