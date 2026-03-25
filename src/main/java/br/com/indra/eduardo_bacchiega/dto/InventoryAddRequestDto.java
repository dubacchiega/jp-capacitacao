package br.com.indra.eduardo_bacchiega.dto;

public record InventoryAddRequestDto(Long quantity, Long productId, Long minQuantity) {
}
