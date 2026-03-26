package br.com.indra.eduardo_bacchiega.product.mapper;

import br.com.indra.eduardo_bacchiega.product.dto.ProductRequestDto;
import br.com.indra.eduardo_bacchiega.product.dto.ProductResponseDto;
import br.com.indra.eduardo_bacchiega.product.model.Product;

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
