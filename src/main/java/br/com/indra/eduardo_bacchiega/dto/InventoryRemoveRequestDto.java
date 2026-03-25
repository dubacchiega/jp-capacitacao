package br.com.indra.eduardo_bacchiega.dto;

import lombok.Builder;

@Builder
public record InventoryRemoveRequestDto(Integer quantity) {
}
