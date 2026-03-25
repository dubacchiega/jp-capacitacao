package br.com.indra.eduardo_bacchiega.service;

import br.com.indra.eduardo_bacchiega.config.JWTUserData;
import br.com.indra.eduardo_bacchiega.dto.UserCreatedResponseDto;
import br.com.indra.eduardo_bacchiega.dto.UserRegisterDto;
import br.com.indra.eduardo_bacchiega.exception.UserAlreadyRegisterException;
import br.com.indra.eduardo_bacchiega.model.User;
import br.com.indra.eduardo_bacchiega.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreatedResponseDto register(UserRegisterDto user) {
        userRepository.findByEmail(user.email()).ifPresent(
                (e) -> {throw new UserAlreadyRegisterException("User already registered with email");}
        );

        User newUser = User.builder()
                .name(user.name())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .build();
        userRepository.save(newUser);

        return UserCreatedResponseDto.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("Email/password incorrect"));
    }

    public static JWTUserData getUser(){
        return (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
