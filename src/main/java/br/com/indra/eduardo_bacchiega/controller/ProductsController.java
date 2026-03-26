package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.docs.ProductsControllerDoc;
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
public class ProductsController implements ProductsControllerDoc {

    private final ProductsService productsService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponseDto> getAll(){
        return productsService.getAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id){
        return productsService.getById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto products){
        return productsService.create(products);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update-price/{id}")
    public ProductResponseDto updatePrice(@PathVariable Long id, @RequestParam(name = "price") BigDecimal price){
        return productsService.updatePrice(id, price);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public ProductResponseDto update(@PathVariable Long id, @RequestBody ProductRequestDto products){
        return productsService.update(id, products);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(Long id){
        productsService.delete(id);
        return;
    }
}
