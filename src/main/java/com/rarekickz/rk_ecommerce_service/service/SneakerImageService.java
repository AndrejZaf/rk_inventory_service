package com.rarekickz.rk_ecommerce_service.service;

import com.rarekickz.rk_ecommerce_service.domain.SneakerImage;

import java.util.Collection;
import java.util.List;

public interface SneakerImageService {

    List<SneakerImage> findAllBySneakerIds(Collection<Long> sneakerIds);
}
