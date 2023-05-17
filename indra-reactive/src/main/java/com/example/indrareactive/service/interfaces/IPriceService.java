package com.example.indrareactive.service.interfaces;

import com.example.indrareactive.model.Price;
import reactor.core.publisher.Mono;

public interface IPriceService {

    Mono<Price> getByDateAndProductAndBrand(String date, Price price);

}
