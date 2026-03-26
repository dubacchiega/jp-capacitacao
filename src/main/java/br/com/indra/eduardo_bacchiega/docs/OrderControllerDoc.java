package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.order.dto.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Pedidos", description = "Controller responsável pelo gerenciamento do pedido após a saída do carrinho.")
public interface OrderControllerDoc {

    @Operation(summary = "Cria um novo pedido", description = "Cria um novo pedido baseado nos itens do carrinho do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Carrinho vazio ou itens indisponíveis no estoque"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public OrderResponseDto newOrder();

    @Operation(summary = "Busca um pedido específico do usuário logado.", description = "Retorna os detalhes de um pedido específico, do usuário logado, pelo seu Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado ou não pertence ao usuário logado")
    })
    public OrderResponseDto getOrder(
            @Parameter(description = "Id do pedido", example = "123")
            Long orderId
    );

    @Operation(summary = "Lista todos os pedidos do usuário", description = "Retorna todos pedidos do usuário logado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos retornado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    })
    public List<OrderResponseDto> getAllOrder();


    @Operation(summary = "Cancela um pedido", description = "Cancela um pedido existente, caso o status seja PAID ou CREATED.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Pedido não pode ser cancelado"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    OrderResponseDto cancelOrder(
            @Parameter(description = "Id do pedido a ser cancelado", example = "456")
            Long orderId
    );
}
