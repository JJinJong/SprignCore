package hello.core.singleton;

public class SingletonService {

    /**
     * 싱글톤패턴의 문제
     * 1. 코드자체가 많이 들어간다.
     * 2. 의존 관계상 클라이언트가 구체 클래스에 의존한다. -> DIP위반
     * 3. 클라이언트가 구체 클래스에 의존하므로 OCP위반할 가능성
     * 4. 테스트가 어렵다
     * 5. 내부 속성을 변경하거나 초기화하기 어렵다.
     * 6. private생성자로 자식 클래스를 만들기 어렵다.
     * 7. 유연성이 떨어진다.
     * 8. 안티패턴으로 불리기도 한다.
     */
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 딱 1개의 객체 인스터만 존재해야하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
