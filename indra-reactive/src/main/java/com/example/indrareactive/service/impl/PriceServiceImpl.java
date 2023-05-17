package com.example.indrareactive.service.impl;

import com.example.indrareactive.model.Price;
import com.example.indrareactive.repository.PriceRepository;
import com.example.indrareactive.service.interfaces.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements IPriceService {

    private final PriceRepository priceRepository;

    @Override
    public Mono<Price> getByDateAndProductAndBrand(String date, Price price) {
        return priceRepository.getByDateAndProductAndBrand(price.getProductId(), price.getBrandId(), date);
    }

}
