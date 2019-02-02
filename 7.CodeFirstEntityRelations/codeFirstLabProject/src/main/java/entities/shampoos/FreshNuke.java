package entities.shampoos;

import entities.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue(value = "FNu")
public class FreshNuke extends BasicShampoo {
    private static final String BRAND="Fresh Nuke";
    private static final BigDecimal PRICE=new BigDecimal(9.33);
    private static final Size SIZE=Size.LARGE;

    public FreshNuke() {
    }

    public FreshNuke( BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
    }
}
