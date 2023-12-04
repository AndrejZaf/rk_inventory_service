package com.rarekickz.rk_ecommerce_service.repository;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

    @Query("SELECT s FROM sneaker s " +
            "LEFT JOIN FETCH s.sneakerImages " +
            "WHERE s.special = true")
    Optional<Sneaker> findBySpecialIsTrue();

    @Query("SELECT s FROM sneaker s " +
            "LEFT JOIN FETCH s.sneakerImages " +
            "WHERE s.id = :sneakerId")
    Optional<Sneaker> findByIdWithImages(Long sneakerId);

    @Query(value = "SELECT s FROM sneaker s " +
            "LEFT JOIN FETCH s.sneakerImages si ",
            countQuery = "SELECT  COUNT(s) FROM sneaker s")
    Page<Sneaker> findAllWithImages(Pageable pageable);
}
