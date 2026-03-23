package br.com.indra.eduardo_bacchiega.dto;

import br.com.indra.eduardo_bacchiega.model.Category;
import lombok.Builder;

@Builder
public record CategoryResponseDto(
        Long id,
        String name,
        Long parentId
) {}