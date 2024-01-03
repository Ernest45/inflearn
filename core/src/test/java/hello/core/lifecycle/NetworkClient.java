package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
// 1. 인터페이스로
public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
    disconnect();
    }

    public NetworkClient() { // 디폴트 생성자

        System.out.println("생성자 호출, uri = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출하는 메서드
    public void connect() {
        System.out.println("connect : " + url);

    }

    public void call(String message) {// 연결이 된 상태에서 콜을 부를 수 있다고 가정하고 메세지 던지기!
        System.out.println("call :" + url +" message = " + message);
        // 실제론 저쪽 서버에 보내는 것
    }
    //서비스 종료 시 호출하는 메서드
    public void disconnect() {
        System.out.println("close : " +url);

    }



}
