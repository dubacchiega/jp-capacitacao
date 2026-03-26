package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.dto.CartItemRequestDto;
import br.com.indra.eduardo_bacchiega.dto.CartResponseDto;
import br.com.indra.eduardo_bacchiega.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// validar exceções para checar se o código corresponde

@Tag(
        name = "Carrinho",
        description = "Controller responsável pelo gerenciamento de carrinho de compras do usuário."
)
public interface CartControllerDoc {


    @Operation(summary = "Busca o carrinho do usuário já logado.", description = "Retorna o carrinho do usuário caso ele esteja logado e caso o carrinho exista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Carrinho não encontrado")
    })
    public CartResponseDto getCart();

    @Operation(summary = "Cria um novo carrinho para o usuário.", description = "Cria um novo carrinho para o usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrinho criado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public CartResponseDto createCart();

    @Operation(summary = "Adiciona itens ao carrinho do usuário.", description = "Adiciona os itens desejado no carrinho do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do item inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public CartResponseDto addItems(
            @Parameter(description = "Dados do item a ser adicionado.")
            CartItemRequestDto request);

    @Operation(summary = "Remove um item do carrinho do usuário.", description = "Remove o item desejado do carrinho do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removido com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public CartResponseDto deleteItems(
            @Parameter(description = "Id do produto a ser removido.", example = "7")
            Long productId);
}
