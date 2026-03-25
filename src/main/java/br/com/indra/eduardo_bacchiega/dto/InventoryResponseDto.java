package br.com.indra.eduardo_bacchiega.dto;

public record InventoryResponseDto(
        Long id,
        Long productId,
        String productName,
        Long quantity,
        Long minQuantity,
        boolean isLowStock
) {
}
