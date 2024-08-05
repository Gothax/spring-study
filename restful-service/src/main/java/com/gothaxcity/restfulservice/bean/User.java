package com.gothaxcity.restfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, max=20, message = "이름은 최소 2글자, 최대 20자로 입력해 주세요")
    private String name;
    @Past(message = "등록일은 미래 날짜를 입력할 수 없습니다")
    private Date joinDate;

    private String password;
    private String ssn;
}
