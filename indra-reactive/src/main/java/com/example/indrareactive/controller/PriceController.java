package com.example.indrareactive.controller;

import com.example.indrareactive.dto.PriceDTO;
import com.example.indrareactive.mapper.PriceMapper;
import com.example.indrareactive.model.Price;
import com.example.indrareactive.service.interfaces.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/price")
public class PriceController {

    private final IPriceService iPriceService;
    private final PriceMapper priceMapper;

    @GetMapping(value = "/rate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<PriceDTO> getRate(
            @RequestParam @DateTimeFormat(pattern = "dd HH:mm") String date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {
        return iPriceService.getByDateAndProductAndBrand(date, Price.builder().productId(productId).brandId(brandId).build())
                .map(priceMapper::priceToPriceDto);
    }
}