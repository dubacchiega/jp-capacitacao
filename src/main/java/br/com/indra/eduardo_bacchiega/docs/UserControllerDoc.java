package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.dto.LoginRequestDto;
import br.com.indra.eduardo_bacchiega.dto.LoginResponseDto;
import br.com.indra.eduardo_bacchiega.dto.UserCreatedResponseDto;
import br.com.indra.eduardo_bacchiega.dto.UserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários e Autenticação", description = "Controller para registro de novos usuários e login.")
public interface UserControllerDoc {

    @Operation(summary = "Registra um novo usuário", description = "Cria uma nova conta do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou e-mail já cadastrado")
    })
    public UserCreatedResponseDto register(
            @Parameter(description = "Dados para cadastro do novo usuário")
            UserRegisterDto user
    );

    @Operation(summary = "Realiza o login", description = "Faz o login do usuário com e-mail e senha e retorna um token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso. Retorna o Token JWT."),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public LoginResponseDto login(
            @Parameter(description = "Usuário e senha para acesso do usuário")
            LoginRequestDto request
    );
}
