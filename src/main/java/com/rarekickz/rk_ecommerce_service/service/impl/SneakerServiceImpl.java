package com.rarekickz.rk_ecommerce_service.service.impl;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import com.rarekickz.rk_ecommerce_service.domain.SneakerSize;
import com.rarekickz.rk_ecommerce_service.repository.SneakerRepository;
import com.rarekickz.rk_ecommerce_service.service.SneakerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SneakerServiceImpl implements SneakerService {

    private final SneakerRepository sneakerRepository;

    @Override
    @Transactional
    public Sneaker findFavorite() {
        return sneakerRepository.findBySpecialIsTrue().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Sneaker> findAll() {
        return sneakerRepository.findAll();
    }

    @Override
    public List<Sneaker> findAllByPages(final int page, final int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        final Page<Sneaker> sneakers = sneakerRepository.findAll(pageRequest);
        return sneakers.toList();
    }
}
