package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.product.dto.ProductRequestDto;
import br.com.indra.eduardo_bacchiega.product.dto.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Produtos", description = "Controller para o gerenciamento do catálogo de produtos")
public interface ProductsControllerDoc {

    @Operation(summary = "Lista todos os produtos", description = "Retorna todos os produtos disponíveis.")
    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso")
    @ApiResponse(responseCode = "404", description = "Produtos não encontrado")
    List<ProductResponseDto> getAll();

    @Operation(summary = "Busca produto por Id", description = "Retorna o produto específico com base no Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProductResponseDto getById(
            @Parameter(description = "Id do produto", example = "1")
            Long id
    );

    @Operation(summary = "Cria um novo produto", description = "Adiciona um novo produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do produto inválidos")
    })
    ProductResponseDto create(
            @Parameter(description = "Dados para criação do produto")
            ProductRequestDto products
    );

    @Operation(summary = "Atualiza o preço do produto", description = "Modifica somente o valor do produto específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Valor de preço inválido"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProductResponseDto updatePrice(
            @Parameter(description = "Id do produto", example = "1")
            Long id,
            @Parameter(description = "Novo preço do produto", example = "89.90")
            BigDecimal price
    );

    @Operation(summary = "Atualiza um produto", description = "Atualiza todos os dados do produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do produto inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProductResponseDto update(
            @Parameter(description = "Id do produto", example = "1")
            Long id,
            @Parameter(description = "Novos dados do produto")
            ProductRequestDto products
    );

    @Operation(summary = "Exclui um produto", description = "Remove o produto selecionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    void delete(
            @Parameter(description = "Id único do produto", example = "1") Long id
    );
}