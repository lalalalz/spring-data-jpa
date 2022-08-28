package lalalalz.springdatajpa.repository;

import lalalalz.springdatajpa.entity.Address;
import lalalalz.springdatajpa.entity.Member;
import lalalalz.springdatajpa.entity.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Transactional
    void 페이징_쿼리_테스트2() {
        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST" + i);
            member.setAge(10);
            entityManager.persist(member);
        }
        entityManager.flush();
        entityManager.clear();

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> resultOfPagingQuery = memberRepository.findByAge(10, pageRequest);

        Page<MemberDto> resultOfConverting = resultOfPagingQuery.map(m -> new MemberDto(m.getUsername(), m.getAddress()));

        for (MemberDto memberDto : resultOfConverting) {
            System.out.println("memberDto = " + memberDto);
        }
    }


    @Test
    @Transactional
    void 페이징_쿼리_테스트() {
        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST" + i);
            member.setAge(10);
            entityManager.persist(member);
        }
        entityManager.flush();
        entityManager.clear();

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> resultOfPagingQuery = memberRepository.findByAge(10, pageRequest);

        for (Member member : resultOfPagingQuery) {
            System.out.println("member = " + member);
        }
    }

    @Test
    @Transactional
    void 파라미터_바인딩_이름기반_컬렉션() {
        Member member1 = new Member();
        member1.setUsername("최진수");
        member1.setAddress(new Address("인천", "인항로"));

        Member member2 = new Member();
        member2.setUsername("최민경");
        member2.setAddress(new Address("인천", "인항로"));

        entityManager.persist(member1);
        entityManager.persist(member2);
        entityManager.flush();
        entityManager.clear();

        List<String> usernames = new ArrayList<>();
        usernames.add("최진수");
        usernames.add("최민경");

        List<Member> members = memberRepository.findMemberByParameterNameForCollection(usernames);

        for (Member m : members) {
            System.out.println("m = " + m.getUsername());
        }
    }


    @Test

    @Transactional
    void 파라미터_바인딩_이름기반() {
        Member member = new Member();
        member.setUsername("최진수");
        member.setAddress(new Address("인천", "인항로"));

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<Member> members = memberRepository.findMemberByParameterName("최진수");

        for (Member m : members) {
            System.out.println("m = " + m.getUsername());
        }
    }


    @Test
    @Transactional
    void 파라미터_바인딩_위치기반() {
        Member member = new Member();
        member.setUsername("최진수");
        member.setAddress(new Address("인천", "인항로"));

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<Member> members = memberRepository.findMemberByParameterNumber("최진수");

        for (Member m : members) {
            System.out.println("m = " + m.getUsername());
        }
    }

    @Test
    @Transactional
    void DTO_타입으로_조회하가() {
        Member member = new Member();
        member.setUsername("최진수");
        member.setAddress(new Address("인천", "인항로"));

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<MemberDto> memberDto = memberRepository.findMemberToMemberDtoByUsername("최진수");

        System.out.println("memberDto = " + memberDto);
    }


    @Test
    @Transactional
    void 임베디드_타입으로_조회하가() {
        Member member = new Member();
        member.setUsername("최진수");
        member.setAddress(new Address("인천", "인항로"));

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<Address> addressOfMember = memberRepository.findAddressByUsername("최진수");

        System.out.println("addressOfMember = " + addressOfMember);
    }


    @Test
    @Transactional
    void 쿼리_메서드_Limit_조회_테스트() {

        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST");
            entityManager.persist(member);
        }
        entityManager.flush();
        entityManager.clear();

        List<Member> findMemberByTop3 = memberRepository.findTop3MemberByUsername("TEST");
        System.out.println("findMembers = " + findMemberByTop3.size());

        List<Member> findMemberByTop = memberRepository.findTopMemberByUsername("TEST");
        System.out.println("findMembers = " + findMemberByTop.size());

        List<Member> findMemberByFirst = memberRepository.findFirst3MemberByUsername("TEST");
        System.out.println("findMembers = " + findMemberByFirst.size());

        List<Member> findMemberByFirst3 = memberRepository.findFirstMemberByUsername("TEST");
        System.out.println("findMembers = " + findMemberByFirst3.size());
    }


    @Test
    @Transactional
    void 쿼리_메서드_distinct_조회_테스트() {

        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST");
            entityManager.persist(member);
        }
        entityManager.flush();
        entityManager.clear();

        List<Member> findMember = memberRepository.findDistinctMemberByUsername("TEST");
        System.out.println("findMembers = " + findMember.size());
    }

    @Test
    @Transactional(readOnly = true)
    void 쿼리_메서드_삭제_테스트() {

        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST");
            entityManager.persist(member);
        }
        entityManager.flush();
        entityManager.clear();

        long numberOfRemoved = memberRepository.deleteMemberByUsername("TEST");
        System.out.println("numberOfRemoved = " + numberOfRemoved);
    }

    @Test
    @Transactional(readOnly = true)
    void 쿼리_메서드_EXISTS_테스트() {

        Member member = new Member();
        member.setUsername("TEST");
        entityManager.persist(member);

        entityManager.flush();
        entityManager.clear();

        boolean hasTestMember = memberRepository.existsMemberByUsername("TEST");
        System.out.println("hasTestMember = " + hasTestMember);

        boolean hasNoneMember = memberRepository.existsMemberByUsername("NONE");
        System.out.println("hasNoneMember = " + hasNoneMember);
    }


    @Test
    @Transactional(readOnly = true)
    void 쿼리_메서드_갯수_테스트() {

        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setUsername("TEST");
            entityManager.persist(member);
        }

        entityManager.flush();
        entityManager.clear();

        Long count = memberRepository.countMemberByUsername("TEST");
        System.out.println("count = " + count);
    }

    @Test
    @Transactional(readOnly = true)
    void 쿼리_메서드_조회_테스트() {
        Member member = new Member();
        member.setUsername("TEST");

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<Member> findMember = memberRepository.findMemberByNamedQuery("TEST");

        for (Member m : findMember) {
            System.out.println("m = " + m.getUsername());
        }
    }

    @Test
    @Transactional(readOnly = true)
    void 쿼리_메서드_네임드_쿼리_테스트() {
        Member member = new Member();
        member.setUsername("TEST");

        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        List<Member> findMember = memberRepository.findMemberByNamedQuery("TEST");

        for (Member m : findMember) {
            System.out.println("m = " + m.getUsername());
        }
    }

@Test
@Transactional(readOnly = true)
void 쿼리_메서드_직접_지정_쿼리_테스트() {
    Member member = new Member();
    member.setUsername("TEST");

    entityManager.persist(member);
    entityManager.flush();
    entityManager.clear();

    List<Member> findMember = memberRepository.findMemberByParameterNumber("TEST");

    for (Member m : findMember) {
        System.out.println("m.getUsername() = " + m.getUsername());
    }
}

    @Test
    @Transactional
    void 공통_메서드_사용_테스트() {

        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            entityManager.persist(member);
        }

        entityManager.flush();
        entityManager.clear();

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(5);
    }


//    @Test
//    void 새로운_엔티티_확인_테스트_직접할당() {
//        Item item = new Item("A");
////        item.setId("A");
//
//        itemRepository.save(item);
//    }
//
//    @Test
//    void 새로운_엔티티_확인_테스트_자동할당() {
//        Item item = new Item();
//        itemRepository.save(item);
//    }


//    @Test
//    @Transactional
//    void 사용자_정의_메서드() {
//
//        Member memberA = new Member();
//        memberA.setUsername("memberA");
//
//        Member memberB = new Member();
//        memberB.setUsername("memberA");
//
//        Member memberC = new Member();
//        memberC.setUsername("memberA");
//
//        Member memberD = new Member();
//        memberD.setUsername("memberA");
//
//        memberRepository.save(memberA);
//        memberRepository.save(memberB);
//        memberRepository.save(memberC);
//        memberRepository.save(memberD);
//
//        entityManager.flush();
//        entityManager.clear();
//
//        List<Member> members = memberRepository.customFindMembers();
//        members.iterator().forEachRemaining(m -> System.out.println(m));
//        assertThat(members.size()).isEqualTo(4);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void Auditing_직접_구현() throws InterruptedException {
//
//        Member memberA = new Member();
//        memberA.setUsername("memberA");
//
//        memberRepository.save(memberA); // @PrePersist
//
//        Thread.sleep(100);
//        memberA.setUsername("memberB");
//
//        entityManager.flush(); // @PreUpdate
//        entityManager.clear();
//
//        Member findMember = memberRepository.findById(memberA.getId())
//                .orElse(null);
//
//        if (findMember != null) {
//            System.out.println("findMember.CreatedDate = " + findMember.getCreatedDate());
//            System.out.println("findMember.UpdatedDate = " + findMember.getUpdatedDate());
//        }
//
//        else {
//            System.out.println("회원을 찾을 수 없습니다.");
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void Auditing_사용() throws InterruptedException {
//
//        Member memberA = new Member();
//        memberA.setUsername("memberA");
//
//        memberRepository.save(memberA); // @PrePersist
//
//        Thread.sleep(100);
//        memberA.setUsername("memberB");
//
//        entityManager.flush(); // @PreUpdate
//        entityManager.clear();
//
//        Member findMember = memberRepository.findById(memberA.getId())
//                .orElse(null);
//
//        if (findMember != null) {
//            System.out.println("findMember.CreatedDate = " + findMember.getCreatedDate());
//            System.out.println("findMember.CreatedBy = " + findMember.getCreatedBy());
//            System.out.println("findMember.UpdatedDate = " + findMember.getUpdatedDate());
//            System.out.println("findMember.UpdatedBy = " + findMember.getUpdatedBy());
//        }
//
//        else {
//            System.out.println("회원을 찾을 수 없습니다.");
//        }
//    }
}