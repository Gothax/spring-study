package jpabook.jpashop.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름을 비울 수 없습니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
