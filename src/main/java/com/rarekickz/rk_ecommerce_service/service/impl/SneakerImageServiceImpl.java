package com.rarekickz.rk_ecommerce_service.service.impl;

import com.rarekickz.rk_ecommerce_service.domain.SneakerImage;
import com.rarekickz.rk_ecommerce_service.repository.SneakerImageRepository;
import com.rarekickz.rk_ecommerce_service.service.SneakerImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SneakerImageServiceImpl implements SneakerImageService {

    private final SneakerImageRepository sneakerImageRepository;

    @Override
    public List<SneakerImage> findAllBySneakerIds(final Collection<Long> sneakerIds) {
        return sneakerImageRepository.findBySneakerIdIn(sneakerIds);
    }
}
