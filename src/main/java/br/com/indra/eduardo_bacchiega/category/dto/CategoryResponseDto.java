package br.com.indra.eduardo_bacchiega.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
        Long id,
        String name,
        Long parentId
) {}