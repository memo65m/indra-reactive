package com.example.indrareactive.mapper;

import com.example.indrareactive.dto.PriceDTO;
import com.example.indrareactive.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDTO priceToPriceDto(Price price);

}