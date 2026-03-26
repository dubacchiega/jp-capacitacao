package br.com.indra.eduardo_bacchiega.docs;


import br.com.indra.eduardo_bacchiega.coupon.dto.CouponRequestDto;
import br.com.indra.eduardo_bacchiega.coupon.dto.CouponResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Cupons", description = "Controller para o gerenciamento de cupons de desconto do sistema")
public interface CouponControllerDoc {

    @Operation(summary = "Cria um novo cupom", description = "Cadastra um novo cupom de desconto com suas regras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupom criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Cupom inválidos ou código já existente")
    })
    CouponResponseDto newCoupon(
            @Parameter(description = "Dados para criação do novo cupom")
            CouponRequestDto request
    );

    @Operation(summary = "Lista todos os cupons", description = "Retorna uma lista com todos os cupons cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de cupons retornada com sucesso")
    List<CouponResponseDto> getAll();
}
