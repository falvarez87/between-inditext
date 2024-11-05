package com.between.ecommerce.application;

import com.between.ecommerce.domain.Price;
import com.between.ecommerce.infraestructure.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Encuentra el ultimo precio aplicable para un producto y marca en una fecha específica.
     * @param productId ID del producto.
     * @param brandId ID de la marca.
     * @param applicationDate Fecha de consulta.
     * @return Un Optional que contiene el precio aplicable, si existe.
     */
    public Price findLastPrice(OffsetDateTime applicationDate, Long productId, Long brandId) {

        return priceRepository.findLastPrice(applicationDate,productId,brandId);
    }
}
