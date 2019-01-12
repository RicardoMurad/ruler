package com.ruler.models.rules;

import com.ruler.models.Item;
import com.ruler.models.Rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class AppleTVDiscountRule implements Rule {

    private final Integer GROUP_SIZE = 2;
    private final String APPLE_TV_SKU = "atv";


    @Override
    public boolean isMatch(List<Item> itens) {
        return filterElebibleItens(itens).size() >= GROUP_SIZE;
    }

    @Override
    public BigDecimal execute(List<Item> itens) {
        BigDecimal totals = itens.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        List<Item> appleTvs = filterElebibleItens(itens);

        BigDecimal price = appleTvs.get(0).getPrice();

        return price.multiply(BigDecimal.valueOf(appleTvs.size() / 3));
    }

    private List<Item> filterElebibleItens(List<Item> itens) {
        return itens.stream()
                .filter(item -> item.getSku().equals(APPLE_TV_SKU))
                .collect(Collectors.toList());
    }

}
