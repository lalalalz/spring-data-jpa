package lalalalz.springdatajpa;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public void callHello() {
        memberRepository.hello();
    }
}
