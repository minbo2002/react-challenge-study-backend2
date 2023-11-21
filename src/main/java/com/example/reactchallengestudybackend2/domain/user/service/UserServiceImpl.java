package com.example.reactchallengestudybackend2.domain.user.service;

import com.example.reactchallengestudybackend2.common.exception.CustomApiException;
import com.example.reactchallengestudybackend2.common.exception.ResponseCode;
import com.example.reactchallengestudybackend2.common.utils.encoder.PasswordEncoderUtil;
import com.example.reactchallengestudybackend2.domain.user.dto.request.SignUpDto;
import com.example.reactchallengestudybackend2.domain.user.dto.response.UserInfoDto;
import com.example.reactchallengestudybackend2.domain.user.entity.Role;
import com.example.reactchallengestudybackend2.domain.user.entity.User;
import com.example.reactchallengestudybackend2.domain.user.repository.RoleRepository;
import com.example.reactchallengestudybackend2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    // 회원가입
    @Transactional
    @Override
    public void registerUser(SignUpDto signUpDto) {
        log.info("userService registerUser run");

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new CustomApiException(ResponseCode.USERNAME_ALREADY_EXISTS);
        }
        // create user object
        // test role : user
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new CustomApiException(ResponseCode.USERNAME_ALREADY_EXISTS));
        log.info("role: {}", role);

        User user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoderUtil.encodePassword(signUpDto.getPassword()))
                .role(User.RoleType.USER)
                .roles(Collections.singleton(role))
                .build();

        userRepository.save(user);
    }

    // 회원정보 조회
    @Override
    public UserInfoDto getUser(Long userId) {
        log.info("userService getUser run");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException(ResponseCode.USER_NOT_FOUND));

        return UserInfoDto.from(user);
    }
}
