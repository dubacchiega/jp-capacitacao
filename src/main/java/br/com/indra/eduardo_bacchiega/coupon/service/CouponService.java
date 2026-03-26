package br.com.indra.eduardo_bacchiega.coupon.service;

import br.com.indra.eduardo_bacchiega.coupon.dto.ValidCoupon;
import br.com.indra.eduardo_bacchiega.coupon.model.Coupon;
import br.com.indra.eduardo_bacchiega.coupon.repository.CouponRepository;
import br.com.indra.eduardo_bacchiega.enums.DiscountType;
import br.com.indra.eduardo_bacchiega.exception.CouponExistsException;
import br.com.indra.eduardo_bacchiega.exception.CouponInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    public Coupon newCoupon(Coupon newCoupon){
        couponRepository.findByCode(newCoupon.getCode()).ifPresent(
                (e) -> {
                    throw new CouponExistsException("This coupon already exists");
                }
        );

        Coupon coupon = Coupon.builder()
                .code(newCoupon.getCode())
                .discountType(newCoupon.getDiscountType())
                .discountValue(newCoupon.getDiscountValue())
                .expiration(newCoupon.getExpiration())
                .usageLimit(newCoupon.getUsages())
                .usages(0)
                .build();
        return coupon;
    }

    public List<Coupon> getAllCoupon(){
        return couponRepository.findAll();
    }

    public ValidCoupon valid(Coupon coupon){
        LocalDateTime today = LocalDateTime.now();
        if (today.isAfter(coupon.getExpiration())){
            throw new CouponInvalidException("This coupon is not valid");
        }
        return ValidCoupon.builder()
                .isValid(true)
                .discountType(coupon.getDiscountType())
                .discountValue(coupon.getDiscountValue())
                .build();
    }
}
