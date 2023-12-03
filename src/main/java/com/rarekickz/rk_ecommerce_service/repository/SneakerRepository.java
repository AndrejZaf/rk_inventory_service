package com.rarekickz.rk_ecommerce_service.repository;

import com.rarekickz.rk_ecommerce_service.domain.Sneaker;
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
}
