package com.sparta.magazine.service;

import com.sparta.magazine.dto.SignupRequestDto;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String userEmail = requestDto.getUserEmail();
        Optional<User> byUserEmail = userRepository.findByUserEmail(userEmail);
        if (byUserEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 email이 존재합니다.");
        }
        Optional<User> byNickname = userRepository.findByNickname(requestDto.getNickname());
        if (byNickname.isPresent()) {
            throw new IllegalArgumentException("중복된 nickname이 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        User user = new User(userEmail, password, nickname);
        userRepository.save(user);
    }
}