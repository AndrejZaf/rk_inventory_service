package com.rarekickz.rk_ecommerce_service.service;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;

import java.util.List;

public interface SneakerService {

    Sneaker findPremiumSneaker();

    Sneaker findById(Long sneakerId);

    List<Sneaker> findAll();

    List<Sneaker> findAllByPages(int page, int size);
}
