package br.com.indra.eduardo_bacchiega.service;

import br.com.indra.eduardo_bacchiega.dto.InventoryAddRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryRemoveRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryResponseDto;
import br.com.indra.eduardo_bacchiega.enums.TransactionType;
import br.com.indra.eduardo_bacchiega.exception.InventoryCriticalQuantityException;
import br.com.indra.eduardo_bacchiega.exception.InventoryExistsException;
import br.com.indra.eduardo_bacchiega.exception.InventoryNotFoundException;
import br.com.indra.eduardo_bacchiega.exception.ProductsNotFound;
import br.com.indra.eduardo_bacchiega.mapper.InventoryMapper;
import br.com.indra.eduardo_bacchiega.model.Inventory;
import br.com.indra.eduardo_bacchiega.model.InventoryTransaction;
import br.com.indra.eduardo_bacchiega.model.Product;
import br.com.indra.eduardo_bacchiega.repository.InventoryRepository;
import br.com.indra.eduardo_bacchiega.repository.InventoryTransactionRepository;
import br.com.indra.eduardo_bacchiega.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final ProductsRepository productsRepository;

    public InventoryResponseDto add(Long id, InventoryAddRequestDto request){
        Product product = productsRepository.findById(request.productId()).orElseThrow(
                () -> new ProductsNotFound("Product not found")
        );

        Inventory saved;
        Optional<Inventory> optInventory = inventoryRepository.findById(id);
        if (optInventory.isPresent()){
            Inventory exists = optInventory.get();
            exists.setQuantity(exists.getQuantity() + request.quantity());
            saved = inventoryRepository.save(exists);

        } else {
            saved = inventoryRepository.save(
                    Inventory.builder()
                            .quantity(request.quantity())
                            .product(product)
                            .minQuantity(request.minQuantity())
                            .build());
        }

        InventoryTransaction transaction = InventoryTransaction.builder()
                .inventory(saved)
                .type(TransactionType.PURCHASE)
                .quantity(request.quantity())
                .build();

        inventoryTransactionRepository.save(transaction);
        return InventoryMapper.toDto(saved);
    }

    public InventoryResponseDto remove(Long id, InventoryRemoveRequestDto request){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryNotFoundException("Inventory not found")
        );

        if (inventory.getQuantity() < request.quantity()){
            throw new InventoryCriticalQuantityException("Insufficient stock.");
        }
        inventory.setQuantity(inventory.getQuantity() - request.quantity());
        Inventory saved = inventoryRepository.save(inventory);

        InventoryTransaction transaction = InventoryTransaction.builder()
                .inventory(saved)
                .type(TransactionType.SALE)
                .quantity(request.quantity())
                .build();

        inventoryTransactionRepository.save(transaction);
        return InventoryMapper.toDto(saved);

    }

    public InventoryResponseDto getById(Long id){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                () -> new InventoryNotFoundException("Inventory not found")
        );
        return InventoryMapper.toDto(inventory);
    }
}
