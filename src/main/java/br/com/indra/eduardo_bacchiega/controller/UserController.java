package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.config.TokenService;
import br.com.indra.eduardo_bacchiega.dto.LoginRequestDto;
import br.com.indra.eduardo_bacchiega.dto.LoginResponseDto;
import br.com.indra.eduardo_bacchiega.dto.UserCreatedResponseDto;
import br.com.indra.eduardo_bacchiega.dto.UserRegisterDto;
import br.com.indra.eduardo_bacchiega.model.User;
import br.com.indra.eduardo_bacchiega.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public UserCreatedResponseDto register(UserRegisterDto user){
        return userService.register(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponseDto login(LoginRequestDto request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDto(token);
    }




}

