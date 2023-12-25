package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 수많은 싱글톤 패턴 만드는 방법 중 객체 하나를 미리 만들어놓고 그걸 반환하는 안전한 방식 중 하나

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
