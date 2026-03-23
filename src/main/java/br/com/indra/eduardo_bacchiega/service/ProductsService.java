package br.com.indra.eduardo_bacchiega.service;

import br.com.indra.eduardo_bacchiega.dto.ProductRequestDto;
import br.com.indra.eduardo_bacchiega.dto.ProductResponseDto;
import br.com.indra.eduardo_bacchiega.exception.CategoryNotFoundException;
import br.com.indra.eduardo_bacchiega.exception.ProductsNotFound;
import br.com.indra.eduardo_bacchiega.mapper.ProductMapper;
import br.com.indra.eduardo_bacchiega.model.Category;
import br.com.indra.eduardo_bacchiega.model.PriceHistoric;
import br.com.indra.eduardo_bacchiega.model.Product;
import br.com.indra.eduardo_bacchiega.repository.CategoryRepository;
import br.com.indra.eduardo_bacchiega.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    // refactor: colocar o nome da categoria
    public List<ProductResponseDto> getAll(){
        List<Product> entity = productsRepository.findAll();
        return entity.stream().map(
                ProductMapper::toDto
        ).toList();
    }

    public ProductResponseDto create(ProductRequestDto products){
        Category category = categoryRepository.findById(products.categoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found")
        );
        Product entity = ProductMapper.toProduct(products);
        entity.setCategory(category);

        productsRepository.save(entity);
        return ProductMapper.toDto(entity);
    }

    public ProductResponseDto getById(Long id) {
        Product product = productsRepository.findById(id).orElseThrow(
                () -> new ProductsNotFound("Id not found")
        );
        return ProductMapper.toDto(product);
    }

    public ProductResponseDto update(Long id, ProductRequestDto products) {
        Product oldProduct = productsRepository.findById(id).orElseThrow(() -> new ProductsNotFound("ID not found"));
        oldProduct.setName(products.name());
        oldProduct.setDescription(products.description());
        oldProduct.setPrice(products.price());
        oldProduct.setBarCode(products.barCode());
        productsRepository.save(oldProduct);
        return ProductMapper.toDto(oldProduct);
    }

    public ProductResponseDto updatePrice(Long id, BigDecimal price){
        Product updatedProduct = productsRepository.findById(id).orElseThrow(() -> new ProductsNotFound("ID not found"));

        // Flush -> Serve para que eu force a mudança no banco de dados e use esse dado que eu usei na mesma função
        final var historic = new PriceHistoric();
        historic.setOldPrice(updatedProduct.getPrice());
        historic.setProduct(updatedProduct);
        historic.setNewPrice(price);

        updatedProduct.setPrice(price);

        productsRepository.save(updatedProduct);

        // posso já dar um get direto no preço
        productsRepository.flush();

        return ProductMapper.toDto(updatedProduct);
    }

    public void delete(Long id){
        productsRepository.deleteById(id);
    }
}
