package br.com.indra.eduardo_bacchiega.mapper;

import br.com.indra.eduardo_bacchiega.dto.ProductRequestDto;
import br.com.indra.eduardo_bacchiega.dto.ProductResponseDto;
import br.com.indra.eduardo_bacchiega.model.Product;

public class ProductMapper {

    public static Product toProduct(ProductRequestDto dto){
        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .barCode(dto.barCode())
                .build();
    }

    public static ProductResponseDto toDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .barCode(product.getBarCode())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
