package br.com.indra.eduardo_bacchiega.coupon.controller;

import br.com.indra.eduardo_bacchiega.coupon.dto.CouponRequestDto;
import br.com.indra.eduardo_bacchiega.coupon.dto.CouponResponseDto;
import br.com.indra.eduardo_bacchiega.coupon.service.CouponService;
import br.com.indra.eduardo_bacchiega.docs.CouponControllerDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController implements CouponControllerDoc {

    private final CouponService couponService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CouponResponseDto newCoupon(CouponRequestDto request){
        return couponService.newCoupon(request);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CouponResponseDto> getAll(){
        return couponService.getAllCoupon();
    }
}
