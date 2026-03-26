package br.com.indra.eduardo_bacchiega.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(@NotBlank String name, Long parentId) {
}
