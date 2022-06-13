package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /*
     * 애플리케이션의 실제 동작에 필요한 구현객체를 생성한다.
     * 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입한다.
     * 객체의 생성과 연결은 AppConfig가 담당
     * DIP완성 MemberServiceImpl은 MemberRepository인 추상에만 의존, 이제 구체 클래스를 몰라도 된다.
     * 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확이 분리되었다.
     * */

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean -> new MemoryMemberRepository() 과연 싱글톤 패턴이 깨질까?

    // 생성자 주입
    @Bean //스프링 컨테이너에 등록됨
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
