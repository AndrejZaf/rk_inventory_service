package com.rarekickz.rk_ecommerce_service.service.impl;

import com.rarekickz.rk_ecommerce_service.domain.Brand;
import com.rarekickz.rk_ecommerce_service.repository.BrandRepository;
import com.rarekickz.rk_ecommerce_service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
