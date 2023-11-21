package com.example.reactchallengestudybackend2.domain.user.service;

import com.example.reactchallengestudybackend2.domain.user.dto.request.SignUpDto;
import com.example.reactchallengestudybackend2.domain.user.dto.response.UserInfoDto;

public interface UserService {

    void registerUser(SignUpDto signUpDto);

    UserInfoDto getUser(Long userId);
}
