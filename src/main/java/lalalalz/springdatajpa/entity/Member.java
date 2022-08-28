package lalalalz.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
@NamedQuery(
        name = "Member.findMemberByNamedQuery",
        query = "select m from Member m where m.username = :username"
)
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private Integer age;

    @Embedded
    private Address address;

}
