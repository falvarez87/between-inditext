package com.between.ecommerce.application;

import com.between.ecommerce.domain.Price;
import com.between.ecommerce.infraestructure.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static javax.xml.bind.DatatypeConverter.parseDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void testFindApplicablePrice_ReturnsHighestPriorityPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price highPriorityPrice = new Price(1, Date.from(date.minusDays(1).toInstant(ZoneOffset.UTC)),
                Date.from(date.plusDays(1).toInstant(ZoneOffset.UTC)), 2, 35455, 1, 25.45d, "EUR");

        when(priceRepository.findLastPrice(date.atOffset(ZoneOffset.UTC), 35455L, 1L)).
                thenReturn(highPriorityPrice);

        Price result = priceService.findLastPrice(date.atOffset(ZoneOffset.UTC),35455L, 1L);

        assertEquals(highPriorityPrice, result);
    }

    @Test
    void testFindApplicablePrice_NoPriceFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findLastPrice(date.atOffset(ZoneOffset.UTC),35455L, 1L)).thenReturn(null);

        Price result = priceService.findLastPrice(date.atOffset(ZoneOffset.UTC),35455L, 1L);

        assertNull(result);
    }
}
