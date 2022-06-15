package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
////    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
////    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    /*할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야한다.
//    *
//    * 문제점 발견
//    * 역할과 구현을 충실하게 분리 => OK
//    * 다형성 활용, 인터페이스와 구현객체를 분리 => OK
//    * OCP, DIP같은 객체지향 설계 원칙을 충실히 준수했다? => 그렇게 보이지만 아니다.
//    * 클래스 의존관계를 분석하면 추상(인터페이스) 뿐만아니라 구체(구현) 클래스에도 의존하고 있다.
//    * - 추상(인터페이스)의존 : DisocountPolicy
//    * - 구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy
//    * 따라서 DIP 위반
//    *
//    * 또한 FixDicountPolicy에서 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl소스코드도 함께 변경해야 하고 OCP 위반
//    * */
//
////    누군가 구현객체를 대신 생성해서 주입해줘야 한다
//    private DiscountPolicy discountPolicy;


//    DIP을 준수하고 있다. 추상화에 의존
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //@RequiredArgsConstructor이 아래 생성자와 동일한 코드를 만들어줌
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        // 단일 책임 원칙이 잘 지켜진 예
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
