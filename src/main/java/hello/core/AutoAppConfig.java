package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration // @Component가 붙어있음
@ComponentScan(
        // 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공

//        basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치를 지정. 이 패키지를 포함해서 하위 패키지를 모두 탐색
//        basePackageClasses = AutoAppConfig.class,
        // 기존 수동으로 등록한 예제를 작동하지 않기 위해서 쓴거임, 실무에선 굳이 안함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //spring.main.allow-bean-definition-overriding=true
    //스프링 수동 등록과 자동등록시 자동으로 오버라이딩 되게 해주는 설정, application.properties에 넣어주면된다
    //스프링 부트는 기본적으로 수동등록과 자동등록의 이름이 겹치면 예외를 발생 시킨다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
