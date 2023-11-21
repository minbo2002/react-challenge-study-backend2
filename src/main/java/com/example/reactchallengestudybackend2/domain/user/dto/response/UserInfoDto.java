package com.example.reactchallengestudybackend2.domain.user.dto.response;

import com.example.reactchallengestudybackend2.domain.user.entity.Role;
import com.example.reactchallengestudybackend2.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class UserInfoDto {

    private String name;
    private String email;
    private User.RoleType role;
    private Set<Role> roles;

    @Builder
    public UserInfoDto(String name, String email, User.RoleType role, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.roles = roles;
    }

    public static UserInfoDto from(User user) {
        return UserInfoDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .roles(user.getRoles())
                .build();
    }
}
