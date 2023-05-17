package com.example.indrareactive.repository;

import com.example.indrareactive.model.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface PriceRepository extends R2dbcRepository<Price, Long> {

    @Query(value = "SELECT p.* FROM PRICE p LEFT JOIN BRAND b ON p.brand_id = b.id LEFT JOIN PRODUCT pro ON p.product_id = pro.id WHERE pro.id = ?1 AND b.id = ?2 AND ?3 >= FORMATDATETIME(start_date, 'dd HH:mm') AND ?3 <= FORMATDATETIME(end_date, 'dd HH:mm') ORDER BY p.priority DESC LIMIT 1")
    Mono<Price> getByDateAndProductAndBrand(Long productId, Long brandId, String date);

}
