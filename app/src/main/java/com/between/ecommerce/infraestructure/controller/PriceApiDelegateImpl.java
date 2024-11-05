package com.between.ecommerce.infraestructure.controller;

import com.between.ecommerce.application.PriceService;
import com.between.ecommerce.constants.Constants;
import com.between.ecommerce.controller.v1.PriceApiDelegate;
import com.between.ecommerce.domain.Price;
import com.between.ecommerce.dto.v1.PriceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class PriceApiDelegateImpl implements PriceApiDelegate {

    Logger logger = LoggerFactory.getLogger(PriceApiDelegateImpl.class);

    private final PriceService priceService;

    public PriceApiDelegateImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<PriceDTO> getLastPrice(OffsetDateTime date,
                                                 Integer productId,
                                                 Integer brandId) {
        try {
            Price priceEntity = priceService.findLastPrice(date, Long.valueOf(productId), Long.valueOf(brandId));
            PriceDTO priceDTO = convertEntityToDTO(priceEntity);
            if (priceDTO == null) {
                logger.info(Constants.NOT_FOUND_MESSAGE);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(priceDTO);
        } catch (Exception ex) {
            logger.error("exception: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private PriceDTO convertEntityToDTO(Price price) {
        PriceDTO priceDTO = null;
        if (price != null) {
            priceDTO = new PriceDTO();
            priceDTO.setProductId((int) price.getProductId());
            priceDTO.setBrandId((int) price.getBrandId());
            priceDTO.setPriceList(price.getPriceList());
            priceDTO.startDate(price.getStartDate().toInstant().atOffset(ZoneOffset.UTC));
            priceDTO.setEndDate(price.getEndDate().toInstant().atOffset(ZoneOffset.UTC));
            priceDTO.setFinalPrice(price.getPrice());
        }
        return priceDTO;
    }
}