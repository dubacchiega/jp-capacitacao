package br.com.indra.eduardo_bacchiega.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(@NotBlank String name, Long parentId) {
}
