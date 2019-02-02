package entities.shampoos;

import entities.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue(value = "PKa")
public class PinkPanther extends BasicShampoo {
    private static final String BRAND="Pink Panther";
    private static final BigDecimal PRICE=new BigDecimal(8.50);
    private static final Size SIZE=Size.MEDIUM;

    public PinkPanther() {
    }

    public PinkPanther(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
    }
}
