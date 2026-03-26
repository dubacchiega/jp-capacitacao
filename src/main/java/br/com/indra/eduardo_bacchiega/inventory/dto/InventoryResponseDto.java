package br.com.indra.eduardo_bacchiega.inventory.dto;

public record InventoryResponseDto(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        Long minQuantity,
        boolean isLowStock
) {
}
