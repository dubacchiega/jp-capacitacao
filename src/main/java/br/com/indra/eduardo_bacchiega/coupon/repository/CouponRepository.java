package br.com.indra.eduardo_bacchiega.coupon.repository;


import br.com.indra.eduardo_bacchiega.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);
}
