package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.dto.InventoryAddRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryRemoveRequestDto;
import br.com.indra.eduardo_bacchiega.dto.InventoryResponseDto;
import br.com.indra.eduardo_bacchiega.model.Inventory;
import br.com.indra.eduardo_bacchiega.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/add")
    public InventoryResponseDto add (@PathVariable Long id, @RequestBody InventoryAddRequestDto request) {
        return inventoryService.add(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/remove")
    public InventoryResponseDto remove (@PathVariable Long id, @RequestBody InventoryRemoveRequestDto request) {
        return inventoryService.remove(id, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public InventoryResponseDto getAll(@PathVariable Long id) {
        return inventoryService.getById(id);
    }

}
