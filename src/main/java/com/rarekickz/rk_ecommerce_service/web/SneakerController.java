package com.rarekickz.rk_ecommerce_service.web;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import com.rarekickz.rk_ecommerce_service.dto.SneakerDTO;
import com.rarekickz.rk_ecommerce_service.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.rarekickz.rk_ecommerce_service.converter.SneakerConverter.convertFavoriteSneaker;
import static com.rarekickz.rk_ecommerce_service.converter.SneakerConverter.convertToSneakerDTOList;

@RequestMapping("/api/sneaker")
@RestController
@RequiredArgsConstructor
public class SneakerController {

    private final SneakerService sneakerService;

    @GetMapping("/favorite")
    public ResponseEntity<SneakerDTO> getFavoriteSneaker() {
        final Sneaker sneaker = sneakerService.findFavorite();
        return new ResponseEntity<>(convertFavoriteSneaker(sneaker), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SneakerDTO>> getSneakers(@RequestParam int page, @RequestParam int size) {
        // TODO: Add sorting in the future as well
        final List<Sneaker> sneakers = sneakerService.findAllByPages(page, size);
        return new ResponseEntity<>(convertToSneakerDTOList(sneakers), HttpStatus.OK);
    }
}
