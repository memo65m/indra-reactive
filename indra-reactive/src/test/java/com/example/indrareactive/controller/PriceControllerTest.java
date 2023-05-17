package com.example.indrareactive.controller;

import com.example.indrareactive.dto.PriceDTO;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.stream.Stream;


@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Rollback
class PriceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ConnectionFactory connectionFactory;

    @BeforeAll
    private void injectionTest() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populator);
    }

    @ParameterizedTest
    @MethodSource("arg")
    @DisplayName("rate")
    void rate(String date, String startDate, String endDate) {

        String uri = String.format("/price/rate?date=%s&productId=35455&brandId=1", date);

        Flux<PriceDTO> priceDTOFlux = webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(PriceDTO.class)
                .getResponseBody()
                .log();

        StepVerifier.create(priceDTOFlux)
                .expectSubscription()
                .expectNextMatches(priceDTO -> priceDTO.getStartDate().equals(startDate))
                .verifyComplete();
    }

    static Stream<Arguments> arg() {
        return Stream.of(
                Arguments.of("14 10:00", "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("14 16:00", "2020-06-14T15:00:00", "2020-06-14T18:30:00"),
                Arguments.of("14 21:00", "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("15 10:00", "2020-06-15T00:00:00", "2020-06-15T11:00:00"),
                Arguments.of("16 21:00", "2020-06-15T16:00:00", "2020-12-31T23:59:59")
        );
    }

}
