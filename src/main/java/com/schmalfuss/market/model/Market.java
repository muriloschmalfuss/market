package com.schmalfuss.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Market {

    @Id
    public String id;

    private String name;
    private String currency;
    private BigDecimal balance;

    public Market update(Market market) {
        this.setName(market.name);
        this.setCurrency(market.currency);
        this.setBalance(market.balance);
        return this;
    }
}
