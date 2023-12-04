package com.rarekickz.rk_ecommerce_service.web;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import com.rarekickz.rk_ecommerce_service.dto.SneakerDTO;
import com.rarekickz.rk_ecommerce_service.enums.Gender;
import com.rarekickz.rk_ecommerce_service.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rarekickz.rk_ecommerce_service.converter.SneakerConverter.convertPremiumSneaker;
import static com.rarekickz.rk_ecommerce_service.converter.SneakerConverter.convertToSneakerDTO;
import static com.rarekickz.rk_ecommerce_service.converter.SneakerConverter.convertToSneakerDTOList;

@RequestMapping("/api/sneaker")
@RestController
@RequiredArgsConstructor
public class SneakerController {

    private final SneakerService sneakerService;

    @GetMapping("/premium")
    public ResponseEntity<SneakerDTO> getPremiumSneaker() {
        final Sneaker sneaker = sneakerService.findPremiumSneaker();
        return new ResponseEntity<>(convertPremiumSneaker(sneaker), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SneakerDTO> getPremiumSneaker(@PathVariable Long id) {
        final Sneaker sneaker = sneakerService.findById(id);
        return new ResponseEntity<>(convertToSneakerDTO(sneaker), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SneakerDTO>> getSneakers(@RequestParam int page, @RequestParam int size,
                                                        @RequestParam(required = false) List<Long> brandIds, @RequestParam(required = false) List<Double> sizes,
                                                        @RequestParam(required = false) List<Gender> genders) {
        // TODO: Add sorting in the future as well
        final List<Sneaker> sneakers = sneakerService.findAllByPages(page, size, brandIds, genders, sizes);
        return new ResponseEntity<>(convertToSneakerDTOList(sneakers), HttpStatus.OK);
    }
}
