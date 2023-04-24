package com.schmalfuss.market.service;

import com.schmalfuss.market.exception.MarketNotFoundException;
import com.schmalfuss.market.model.Currency;
import com.schmalfuss.market.model.Market;
import com.schmalfuss.market.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

    public Mono<Market> create(Market market) {
        return marketRepository.save(market);
    }

    public Flux<Market> list() {
        return marketRepository.findAll();
    }

    public Mono<Market> update(Market market, String id) {
        return marketRepository.findById(id)
                .flatMap(m -> marketRepository.save(m.update(market)));
    }

    public Mono<?> remove(String id) {
        return marketRepository.findById(id)
                .switchIfEmpty(Mono.error(new MarketNotFoundException("Market not found id " + id)))
                .flatMap(u -> marketRepository.deleteById(id))
                .then();
    }

}
