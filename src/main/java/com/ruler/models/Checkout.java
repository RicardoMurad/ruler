package com.ruler.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkout {

    private final List<Rule> pricingRules;
    private final List<Item> itens = new ArrayList<>();

    public Checkout(List<Rule> pricingRules) {
        this.pricingRules = pricingRules;
    }

        public void scan(Item item) {
            itens.add(item);
        }

    public BigDecimal total() {
        BigDecimal totals = itens.stream()
                                .map(item -> item.getPrice())
                                .reduce(BigDecimal::add)
                                .get();


        BigDecimal discounts = pricingRules.stream()
                                .filter(p -> p.isMatch(itens))
                                .map(p -> p.execute(itens))
                                .reduce(BigDecimal::add)
                                .orElse(BigDecimal.ZERO);



        return totals.subtract(discounts);
    }

}
