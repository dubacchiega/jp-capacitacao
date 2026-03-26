package br.com.indra.eduardo_bacchiega.coupon.mapper;

import br.com.indra.eduardo_bacchiega.coupon.dto.CouponResponseDto;
import br.com.indra.eduardo_bacchiega.coupon.model.Coupon;

public class CouponMapper {

    public static CouponResponseDto toDto(Coupon coupon){
        return CouponResponseDto.builder()
                .code(coupon.getCode())
                .discountType(coupon.getDiscountType())
                .discountValue(coupon.getDiscountValue())
                .expiration(coupon.getExpiration())
                .usageLimit(coupon.getUsageLimit())
                .usage(coupon.getUsages())
                .build();
    }
}
