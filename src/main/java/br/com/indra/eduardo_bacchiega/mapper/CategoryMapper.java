package br.com.indra.eduardo_bacchiega.mapper;

import br.com.indra.eduardo_bacchiega.dto.CategoryRequestDto;
import br.com.indra.eduardo_bacchiega.dto.CategoryResponseDto;
import br.com.indra.eduardo_bacchiega.model.Category;

public class CategoryMapper {

    public static CategoryResponseDto toDto(Category category) {
        // 1. Declaramos a variável começando vazia (null)
        Long parentId = null;

        // 2. Verificamos se existe um pai antes de tentar pegar o ID dele
        if (category.getParent() != null) {
            parentId = category.getParent().getId();
        }

        // 3. Montamos e retornamos o DTO limpo (sem causar o loop infinito!)
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                parentId
        );
    }
}
