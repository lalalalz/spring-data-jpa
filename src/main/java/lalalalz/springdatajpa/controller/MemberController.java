package lalalalz.springdatajpa.controller;


import lalalalz.springdatajpa.entity.Member;
import lalalalz.springdatajpa.entity.MemberDto;
import lalalalz.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/members/{id}")
    public String getUsername(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가진 엔티티가 존재하지 않습니다."));

        System.out.println("member.getUsername() = " + member.getUsername());
        return member.getUsername();
    }

    @ResponseBody
    @GetMapping("/members2/{id}")
    public String getUsernameByDomainClassConverter(@PathVariable("id") Optional<Member> optionalMember) {
        Member member = optionalMember
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 가진 엔티티가 존재하지 않습니다."));

        System.out.println("member.getUsername() = " + member.getUsername());
        return member.getUsername();
    }

    @ResponseBody
    @GetMapping("/members/paging")
    public Page<Member> paging(Pageable pageable) {
        Page<Member> pagingResult = memberRepository.findAll(pageable);
        return pagingResult;
    }

//    @ResponseBody
//    @GetMapping("/members/paging-dto")
//    public Page<MemberDto> paging_dto(Pageable pageable) {
//        return memberRepository.findAll(pageable)
//                .map(MemberDto::new);
//    }

//    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUsername("member" + i);
            memberRepository.save(member);
        }
    }
}
