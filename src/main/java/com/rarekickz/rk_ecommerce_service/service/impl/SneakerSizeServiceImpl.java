package com.rarekickz.rk_ecommerce_service.service.impl;

import com.rarekickz.rk_ecommerce_service.repository.SneakerSizeRepository;
import com.rarekickz.rk_ecommerce_service.service.SneakerSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SneakerSizeServiceImpl implements SneakerSizeService {

    private final SneakerSizeRepository sneakerSizeRepository;

    @Override
    public List<Double> findAllDistinctSizes() {
        return sneakerSizeRepository.findAllDistinctSizes();
    }
}
