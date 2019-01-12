package com.ruler.models;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {

    private final List<Rule> pricingRules;

    public Checkout(List<Rule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scan(Item item) {

    }

    public BigDecimal total() {
        return BigDecimal.ZERO;
    }

}
