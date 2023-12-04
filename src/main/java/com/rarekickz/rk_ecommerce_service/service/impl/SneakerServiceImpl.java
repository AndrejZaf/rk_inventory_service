package com.rarekickz.rk_ecommerce_service.service.impl;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import com.rarekickz.rk_ecommerce_service.domain.SneakerImage;
import com.rarekickz.rk_ecommerce_service.enums.Gender;
import com.rarekickz.rk_ecommerce_service.repository.SneakerRepository;
import com.rarekickz.rk_ecommerce_service.service.SneakerImageService;
import com.rarekickz.rk_ecommerce_service.service.SneakerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.rarekickz.rk_ecommerce_service.specification.SneakerSpecification.createSneakerSpecification;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class SneakerServiceImpl implements SneakerService {

    private final SneakerRepository sneakerRepository;

    private final SneakerImageService sneakerImageService;

    @Override
    @Transactional
    public Sneaker findPremiumSneaker() {
        return sneakerRepository.findBySpecialIsTrue().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Sneaker findById(final Long sneakerId) {
        return sneakerRepository.findByIdWithImages(sneakerId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public List<Sneaker> findAllByPages(final int page, final int size, final List<Long> brandIds, final List<Gender> genders, final List<Double> sizes) {
        PageRequest pageRequest = PageRequest.of(page, size);
        final Specification<Sneaker> sneakerSpecification = createSneakerSpecification(brandIds, genders, sizes);
        final Page<Sneaker> sneakers = sneakerRepository.findAll(sneakerSpecification, pageRequest);
        final List<Long> sneakerIds = sneakers.stream().map(Sneaker::getId)
                .toList();
        final List<SneakerImage> sneakerImages = sneakerImageService.findAllBySneakerIds(sneakerIds);
        final Map<Long, Set<SneakerImage>> sneakerIdToSneakerImages = sneakerImages.stream()
                .collect(groupingBy(sneakerImage -> sneakerImage.getSneaker().getId(), toSet()));
        sneakers.stream()
                .forEach(sneaker -> sneaker.setSneakerImages(sneakerIdToSneakerImages.getOrDefault(sneaker.getId(), Collections.emptySet())));
        return sneakers.toList();
    }
}
