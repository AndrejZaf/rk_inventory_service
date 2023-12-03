package com.rarekickz.rk_ecommerce_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsletterSubscriptionDTO {

    private Long id;
    private String email;
}
