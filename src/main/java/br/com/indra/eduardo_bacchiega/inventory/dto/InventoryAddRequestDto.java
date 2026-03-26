package br.com.indra.eduardo_bacchiega.inventory.dto;

public record InventoryAddRequestDto(Integer quantity, Long productId, Long minQuantity) {
}
