package com.rarekickz.rk_ecommerce_service.service;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import com.rarekickz.rk_ecommerce_service.enums.Gender;

import java.util.List;

public interface SneakerService {

    Sneaker findPremiumSneaker();

    Sneaker findById(Long sneakerId);

    List<Sneaker> findAllByPages(int page, int size, List<Long> brandIds, List<Gender> genders, List<Double> sizes);
}
