package br.com.indra.eduardo_bacchiega.user.controller;

import br.com.indra.eduardo_bacchiega.config.TokenService;
import br.com.indra.eduardo_bacchiega.docs.UserControllerDoc;
import br.com.indra.eduardo_bacchiega.user.dto.LoginRequestDto;
import br.com.indra.eduardo_bacchiega.user.dto.LoginResponseDto;
import br.com.indra.eduardo_bacchiega.user.dto.UserCreatedResponseDto;
import br.com.indra.eduardo_bacchiega.user.dto.UserRegisterDto;
import br.com.indra.eduardo_bacchiega.user.model.User;
import br.com.indra.eduardo_bacchiega.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserControllerDoc {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public UserCreatedResponseDto register(@RequestBody UserRegisterDto user){
        return userService.register(user);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDto(token);
    }
}

