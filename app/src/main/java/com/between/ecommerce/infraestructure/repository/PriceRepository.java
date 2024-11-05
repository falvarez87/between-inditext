package com.between.ecommerce.infraestructure.repository;

import com.between.ecommerce.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {


    /**
     * Encuentra el precio final para un producto y marca específicos en una fecha.
     * @param productId ID del producto.
     * @param brandId ID de la marca.
     * @param applicationDate Fecha de inicio de la consulta.
     * @return Último precio según la fecha.
     */
    @Query(nativeQuery = true, value = "SELECT Top 1 * FROM prices p WHERE p.product_id=:productId AND p.brand_id =:brandId AND :applicationDate BETWEEN p.start_date AND p.end_date ORDER BY p.priority DESC")
    Price findLastPrice(@Param("applicationDate") OffsetDateTime applicationDate, @Param("productId") Long productId, @Param("brandId") Long brandId);
}
