package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*초기화 소멸 인터페이스 단점
                             스프링 전용 인터페이스, 해당 코드가 스프링 전용 인터페이스에 의존한다.
                             내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
                             인터페이스를 사용하는 초기화, 종료방법은 스프링 초창기에 나온 방법으로
                             이제는 거의 사용하지 않는다.
                             implements InitializingBean, DisposableBean
                             */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 최신 스프링에서 가장 권장하는 방법이다.
    // 패키지를 잘보면 javax.annotation.PostConstruct이다. 스프링에 종속적인 기술이 아니라 JSR-250라는 자바 표준이다.
    // 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
    // 컴포넌트 스캔과 잘 어울린다.
    // 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야하면 @BEan의 기능을 사용하자
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
