package br.com.indra.eduardo_bacchiega.inventory.controller;

import br.com.indra.eduardo_bacchiega.docs.InventoryControllerDoc;
import br.com.indra.eduardo_bacchiega.inventory.dto.InventoryAddRequestDto;
import br.com.indra.eduardo_bacchiega.inventory.dto.InventoryRemoveRequestDto;
import br.com.indra.eduardo_bacchiega.inventory.dto.InventoryResponseDto;
import br.com.indra.eduardo_bacchiega.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController implements InventoryControllerDoc {

    private final InventoryService inventoryService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/add")
    public InventoryResponseDto add (@PathVariable Long id, @RequestBody InventoryAddRequestDto request) {
        return inventoryService.add(id, request);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/remove")
    public InventoryResponseDto remove (@PathVariable Long id, @RequestBody InventoryRemoveRequestDto request) {
        return inventoryService.remove(id, request);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public InventoryResponseDto getById(@PathVariable Long id) {
        return inventoryService.getById(id);
    }

}
