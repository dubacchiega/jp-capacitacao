package br.com.indra.eduardo_bacchiega.mapper;

import br.com.indra.eduardo_bacchiega.dto.InventoryResponseDto;
import br.com.indra.eduardo_bacchiega.model.Inventory;

public class InventoryMapper {
    public static InventoryResponseDto toDto(Inventory inventory) {

        boolean lowStock = false;
        if (inventory.getMinQuantity() != null) {
            lowStock = inventory.getQuantity() <= inventory.getMinQuantity();
        }

        return new InventoryResponseDto(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getQuantity(),
                inventory.getMinQuantity(),
                lowStock
        );
    }
}
