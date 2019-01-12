package com.ruler

import com.ruler.models.Checkout
import com.ruler.models.Item
import com.ruler.models.Rule
import spock.lang.Specification

class CheckoutTest extends Specification {

    def "should apply discount for apple tv"() {

        given: "A user doing checkout"

            List<Rule> pricingRules = new ArrayList<>()
            Checkout co = new Checkout(pricingRules);

        when: "He buy 3 apple tvs"

            co.scan(new Item("atv", "Apple TV", BigDecimal.valueOf(109.50)))
            co.scan(new Item("atv", "Apple TV", BigDecimal.valueOf(109.50)))
            co.scan(new Item("atv", "Apple TV", BigDecimal.valueOf(109.50)))

        then: "He will pay the price of 2 only"

            BigDecimal.valueOf(219) == co.total()

    }

    def "should not apply discount for apple tv"() {

        given: "A user doing checkout"

        List<Rule> pricingRules = new ArrayList<>()
        Checkout co = new Checkout(pricingRules);

        when: "He buy 2 apple tvs"

        co.scan(new Item("atv", "Apple TV", BigDecimal.valueOf(109.50)))
        co.scan(new Item("atv", "Apple TV", BigDecimal.valueOf(109.50)))

        then: "He will pay the price of 2 only"

        BigDecimal.valueOf(219) == co.total()

    }


    def "bulk dicount for ipad"() {

        given: "A user doing checkout"

            List<Rule> pricingRules = new ArrayList<>()
            Checkout co = new Checkout(pricingRules)

        when: "He buy more than 4 IPads"

            co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
            co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
            co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
            co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
            co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))


        then: "The price should decrease to 499.99 each"

            BigDecimal.valueOf(2499.95) == co.total()
    }

    def "not apply bulk dicount for ipad"() {

        given: "A user doing checkout"

        List<Rule> pricingRules = new ArrayList<>()
        Checkout co = new Checkout(pricingRules)

        when: "He buy more than <=4 IPads"

        co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
        co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
        co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))
        co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))

        then: "The price should be 549.99 eache"

        BigDecimal.valueOf(2199.96) == co.total()
    }

    def "user win a VGA Adapter with every MacBook Pro sold"() {

        given: "A user doing checkout"

        List<Rule> pricingRules = new ArrayList<>()
        Checkout co = new Checkout(pricingRules)

        when: "He buy macbook pro and vga adapter"

        co.scan(new Item("vga", "VGA adapter ", BigDecimal.valueOf(30.00)))
        co.scan(new Item("mbp", "MacBook Pro", BigDecimal.valueOf(1399.99)))
        co.scan(new Item("ipd", "Super iPad", BigDecimal.valueOf(549.99)))

        then: "We will bundle in a free VGA adapter free of charge with every MacBook Pro sold"

        BigDecimal.valueOf(1949.98) == co.total()
    }

}
