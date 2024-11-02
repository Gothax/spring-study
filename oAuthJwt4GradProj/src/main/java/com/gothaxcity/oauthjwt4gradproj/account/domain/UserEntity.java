package com.gothaxcity.oauthjwt4gradproj.account.domain;

import com.gothaxcity.oauthjwt4gradproj.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false, unique = true) // TODO: 닉네임 자동 생성후 unique 제약조건 추가
    @Column(nullable = false)
    private String nickName;

    private String imageUrl;

    @Enumerated(STRING)
    private Role role;

    @Builder
    public UserEntity(String email, String nickName, String imageUrl, Role role) {
        this.email = email;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.role = role;
    }
}
