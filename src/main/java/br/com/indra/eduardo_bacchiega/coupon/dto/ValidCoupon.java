package br.com.indra.eduardo_bacchiega.coupon.dto;

import br.com.indra.eduardo_bacchiega.enums.DiscountType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ValidCoupon(boolean isValid, DiscountType discountType, BigDecimal discountValue) {
}
