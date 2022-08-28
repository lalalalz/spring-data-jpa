package lalalalz.springdatajpa.repository;

import lalalalz.springdatajpa.entity.Address;
import lalalalz.springdatajpa.entity.Member;
import lalalalz.springdatajpa.entity.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Page<Member> findByAge(int age, Pageable pageable);

    @Query("select m from Member m where m.username in :names")
    List<Member> findMemberByParameterNameForCollection(@Param("names") List<String> usernames);

    @Query("select m from Member m where m.username = :username")
    List<Member> findMemberByParameterName(@Param("username") String username);

    @Query("select m from Member m where m.username = ?1")
    List<Member> findMemberByParameterNumber(String username);


    @Query("select m.address from Member m where m.username = :username")
    List<Address> findAddressByUsername(@Param("username") String username);


@Query(
        "select new lalalalz.springdatajpa.entity.MemberDto(m.username, m.address) " +
                "from Member m " +
                "where m.username = :username"
)
List<MemberDto> findMemberToMemberDtoByUsername(@Param("username") String username);


    List<Member> findMemberByNamedQuery(@Param("username") String username);


    List<Member> findDistinctMemberByUsername(String username);

    List<Member> findTopMemberByUsername(String username);
    List<Member> findTop3MemberByUsername(String username);

    List<Member> findFirstMemberByUsername(String username);
    List<Member> findFirst3MemberByUsername(String username);

    // 특정 이름을 갖는 회원 엔티티의 개수 조회
    long countMemberByUsername(String username);
    // 특정 이름을 갖는 회원 엔티티가 존재하는지 조회
    boolean existsMemberByUsername(String username);
    // 특정 이름을 갖는 회원 엔티티 삭제
    long deleteMemberByUsername(String username);
}
