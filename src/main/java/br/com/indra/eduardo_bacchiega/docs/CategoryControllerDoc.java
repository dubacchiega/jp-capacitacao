package br.com.indra.eduardo_bacchiega.docs;

import br.com.indra.eduardo_bacchiega.dto.CategoryRequestDto;
import br.com.indra.eduardo_bacchiega.dto.CategoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Categoria",
        description = "Controller Responsável pelo gerenciamento de categorias do sistema"
)
public interface CategoryControllerDoc {

    @Operation(summary = "Lista todas as categorias", description = "Retorna uma lista com todas as categorias.")
    @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso.")
    public List<CategoryResponseDto> getAll();


    @Operation(summary = "Cria uma nova categoria", description = "Cria uma nova categoria com os dados enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos")
    })
    CategoryResponseDto createCategory(
            @Parameter(description = "Dados para criação da nova categoria")
            CategoryRequestDto request
    );

    @Operation(summary = "Atualiza uma categoria existente", description = "Caso a categoria exista, atualiza a categoria desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada pelo ID fornecido")
    })
    CategoryResponseDto updateCategory(
            @Parameter(description = "Dados atualizados da categoria")
            CategoryRequestDto request,
            @Parameter(description = "ID da categoria a ser atualizada", example = "1")
            Long id
    );

    @Operation(summary = "Exclui uma categoria", description = "Caso a categoria exista, exclui a categoria desejada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso (Sem conteúdo de retorno)"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada pelo ID fornecido")
    })
    void deleteCategory(
            @Parameter(description = "ID da categoria a ser excluída", example = "1")
            Long id
    );
}
