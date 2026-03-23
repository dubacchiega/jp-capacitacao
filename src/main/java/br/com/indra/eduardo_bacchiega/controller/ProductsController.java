package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.dto.ProductRequestDto;
import br.com.indra.eduardo_bacchiega.dto.ProductResponseDto;
import br.com.indra.eduardo_bacchiega.service.ProductsService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponseDto> getAll(){
        return productsService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id){
        return productsService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto products){
        return productsService.create(products);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update-price/{id}")
    public ProductResponseDto updatePrice(@PathVariable Long id, @RequestParam(name = "price") BigDecimal price){
        return productsService.updatePrice(id, price);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ProductResponseDto update(@RequestParam Long id, @RequestBody ProductRequestDto products){
        return productsService.update(id, products);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(Long id){
        productsService.delete(id);
        return;
    }
}
