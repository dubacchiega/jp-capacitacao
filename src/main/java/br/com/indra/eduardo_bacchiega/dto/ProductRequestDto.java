package br.com.indra.eduardo_bacchiega.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductRequestDto(String name, String description, BigDecimal price, String barCode, Long categoryId) {
}
