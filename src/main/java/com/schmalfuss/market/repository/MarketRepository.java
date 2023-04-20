package com.schmalfuss.market.repository;

import com.schmalfuss.market.model.Market;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MarketRepository extends ReactiveMongoRepository<Market, String> {
}
