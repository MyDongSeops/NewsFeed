package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.UserInfoRequestDto;
import com.sparta.newsfeed.dto.UserInfoResponseDto;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.UserRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserInfoResponseDto userUpdate(User user, UserInfoRequestDto requestDto){
        user.userInfoUpdate(requestDto);

        return new UserInfoResponseDto(user);
    }

    public void passwordUpdate(UserDetailsImpl userDetails, String formPassword) {
        String password = userDetails.getPassword();
    }
}
