package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class MemberForm {

    private Long id;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    public MemberForm() {

    }

    public MemberForm(Member member) {
        id = member.getId();
       name = member.getName();
        city = member.getAddress().getCity();
        street = member.getAddress().getStreet();
        zipcode = member.getAddress().getZipcode();
    }
}
