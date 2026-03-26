package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.dto.InventoryAddRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryRemoveRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Estoque", description = "Controller para gerenciamento de estoque (compra, venda e consulta)")
public interface InventoryControllerDoc {

    @Operation(summary = "Adiciona itens ao estoque.", description = "Adiciona a quantidade informada para o produto mencionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque adicionado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado para adicionar o produto.")
    })
    public InventoryResponseDto add (
            @Parameter(description = "Id do estoque a ser adicionado", example = "1")
            Long id,
            @Parameter(description = "Dados para a adição no estoque.")
            InventoryAddRequestDto request
    );

    @Operation(summary = "Remove itens do estoque", description = "Remove a quantidade do estoque. Valida se tem a quantidade do produto disponível antes de concluir a operação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Baixa no estoque realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Saldo insuficiente ou dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro de estoque não encontrado para o ID fornecido")
    })
    public InventoryResponseDto remove (
            @Parameter(description = "Id do estoque a ser reduzida a quantidade", example = "1")
            Long id,
            @Parameter(description = "Dados para a redução no estoque.")
            InventoryRemoveRequestDto request
    );

    @Operation(summary = "Consulta o estoque por Id", description = "Retorna o estoque informado e os detalhes do produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro de estoque não encontrado.")
    })
    public InventoryResponseDto getById(
            @Parameter(description = "Id do estoque", example = "1")
            Long id
    );

}
