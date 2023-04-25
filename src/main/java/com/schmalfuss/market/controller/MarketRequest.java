package com.schmalfuss.market.controller;

import com.schmalfuss.market.model.Market;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequest {
    private String name;
    private String currency;
    private BigDecimal balance;

    public Market create() {
        Market market = new Market();
        market.setName(this.name);
        market.setCurrency(this.currency);
        market.setBalance(this.balance);
        return market;
    }
}
