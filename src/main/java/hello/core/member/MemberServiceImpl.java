package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService  {
    /*
    * 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository에 의존하지 않는다.
    * 단지 MemberRepository 인터페이스만 의존한다.
    * MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)알 수 없다.
    * MemberServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig에서 결정된다.
    * MemberServiceImpl 은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중
    * */
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
