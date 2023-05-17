package com.example.indrareactive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "Brand no puede ser nulo.")
    @Column("brand_id")
    private Long brandId;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @NotNull(message = "PriceList no puede ser nulo.")
    @Column("price_list")
    private Long priceList;

    @NotNull(message = "ProductId no puede ser nulo.")
    @Column("product_id")
    private Long productId;

    @Column("priority")
    private Integer priority;

    @Column("price")
    private BigDecimal price;

    @Column("curr")
    private String curr;

}
