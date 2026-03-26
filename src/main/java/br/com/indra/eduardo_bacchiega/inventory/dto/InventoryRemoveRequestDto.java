package br.com.indra.eduardo_bacchiega.inventory.dto;

import lombok.Builder;

@Builder
public record InventoryRemoveRequestDto(Integer quantity) {
}
