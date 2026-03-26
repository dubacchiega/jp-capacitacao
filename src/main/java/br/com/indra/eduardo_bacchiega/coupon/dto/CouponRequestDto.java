package br.com.indra.eduardo_bacchiega.coupon.dto;

import br.com.indra.eduardo_bacchiega.enums.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponRequestDto(
        String code,
        DiscountType discountType,
        BigDecimal discountValue,
        LocalDateTime expiration,
        Integer usageLimit
) {
}
