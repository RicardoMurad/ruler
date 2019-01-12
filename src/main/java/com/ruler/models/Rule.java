package com.ruler.models;


import java.math.BigDecimal;
import java.util.List;

public interface Rule {

    boolean isMatch(List<Item> itens);

    BigDecimal execute(List<Item> itens);

}
