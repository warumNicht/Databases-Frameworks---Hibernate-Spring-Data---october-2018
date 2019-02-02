package cars.domain.entities;

import java.math.BigDecimal;

public enum Discount {
    _0_PERCENT(new BigDecimal("0")),
    _5_PERCENT(new BigDecimal("0.05")),
    _10_PERCENT(new BigDecimal("0.1")),
    _15_PERCENT(new BigDecimal("0.15")),
    _20_PERCENT(new BigDecimal("0.2")),
    _30_PERCENT(new BigDecimal("0.3")),
    _40_PERCENT(new BigDecimal("0.4")),
    _50_PERCENT(new BigDecimal("0.5")),
    ;

    private BigDecimal discountPercent;

    Discount(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

}
