package br.com.indra.eduardo_bacchiega.coupon.dto;

import br.com.indra.eduardo_bacchiega.enums.DiscountType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CouponResponseDto(
        String code,
        DiscountType discountType,
        BigDecimal discountValue,
        LocalDateTime expiration,
        Integer usageLimit,
        Integer usage

) {
}
