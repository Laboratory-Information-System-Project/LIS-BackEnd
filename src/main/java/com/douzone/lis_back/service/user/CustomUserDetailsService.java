package com.douzone.lis_back.service.user;

import com.douzone.lis_back.domain.user.User;
import com.douzone.lis_back.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {

        log.info("{}", User.builder().
                username(user.getUsername()).
                password(passwordEncoder.encode(user.getPassword())).
                authority(user.getAuthority()).
                build());
        return User.builder().
                username(user.getUsername()).
                password(passwordEncoder.encode(user.getPassword())).
                authority(user.getAuthority()).
                build();
    }
}
