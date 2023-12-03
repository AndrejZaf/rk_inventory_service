package com.rarekickz.rk_ecommerce_service.service;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SneakerService {

    Sneaker findFavorite();

    List<Sneaker> findAll();

    List<Sneaker> findAllByPages(int page, int size);
}
