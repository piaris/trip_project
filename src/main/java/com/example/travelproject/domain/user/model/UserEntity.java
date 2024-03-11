package com.example.travelproject.domain.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "UserDto")
@Table(name = "user")
public class UserEntity {
    @Id // 기본키: 유니크
    @NotBlank
    private String userId;
    @NotBlank
    private String pwd;

    @Column(unique = true)
    @Email
    private String email;
    @NotBlank
    private String name;
    private String sex;
    private String phone;
    // 일반사용자 / 관리자를 구분용
    private String role;
    // 로그인 유무
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;
}
