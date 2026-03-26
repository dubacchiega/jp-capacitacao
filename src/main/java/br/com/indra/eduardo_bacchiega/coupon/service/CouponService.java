package br.com.indra.eduardo_bacchiega.coupon.service;

import br.com.indra.eduardo_bacchiega.coupon.dto.CouponRequestDto;
import br.com.indra.eduardo_bacchiega.coupon.dto.CouponResponseDto;
import br.com.indra.eduardo_bacchiega.coupon.dto.ValidCoupon;
import br.com.indra.eduardo_bacchiega.coupon.mapper.CouponMapper;
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


    public CouponResponseDto newCoupon(CouponRequestDto newCoupon){
        couponRepository.findByCode(newCoupon.code()).ifPresent(
                (e) -> {
                    throw new CouponExistsException("This coupon already exists");
                }
        );

        Coupon coupon = Coupon.builder()
                .code(newCoupon.code())
                .discountType(newCoupon.discountType())
                .discountValue(newCoupon.discountValue())
                .expiration(newCoupon.expiration())
                .usageLimit(newCoupon.usageLimit())
                .usages(0)
                .build();
        couponRepository.save(coupon);
        return CouponMapper.toDto(coupon);
    }

    public List<CouponResponseDto> getAllCoupon(){
        List<Coupon> all = couponRepository.findAll();
        return all.stream().map(
                CouponMapper::toDto
        ).toList();
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
