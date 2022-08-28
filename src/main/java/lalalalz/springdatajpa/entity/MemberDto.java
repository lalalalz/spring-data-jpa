package lalalalz.springdatajpa.entity;

import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private Address address;

//    public MemberDto(Member member) {
//        this.username = member.getUsername();
//    }
//
    public MemberDto(String username, Address address) {
        this.username = username;
        this.address = address;
    }
}
