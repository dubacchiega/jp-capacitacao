package br.com.indra.eduardo_bacchiega.inventory.mapper;

import br.com.indra.eduardo_bacchiega.inventory.dto.InventoryResponseDto;
import br.com.indra.eduardo_bacchiega.inventory.model.Inventory;

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
