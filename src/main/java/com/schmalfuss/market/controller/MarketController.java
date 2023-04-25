package com.schmalfuss.market.controller;

import com.schmalfuss.market.model.Currency;
import com.schmalfuss.market.model.Market;
import com.schmalfuss.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/markets")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Market>>> list() {
        return marketService.list()
                .collectList()
                .map(m -> ResponseEntity.ok().body(Flux.fromIterable(m)))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<Market>> create(@RequestBody Market market) {
        return marketService.create(market)
                .map(m -> ResponseEntity.ok().body(m));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Market>> update(@RequestBody MarketRequest market, @PathVariable String id) {
        return marketService.update(market.create(), id)
                .map(m -> ResponseEntity.ok().body(m))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> remove(@PathVariable String id) {
        return marketService.remove(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/currency/{id}")
    public Mono<ResponseEntity<Flux<Currency>>> currency(@PathVariable String id) {
        return marketService.currency(id)
                .collectList()
                .map(m -> ResponseEntity.ok().body(Flux.fromIterable(m)))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }
}
