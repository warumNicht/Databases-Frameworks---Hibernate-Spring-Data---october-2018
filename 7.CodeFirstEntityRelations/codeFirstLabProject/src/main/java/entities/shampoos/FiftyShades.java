package entities.shampoos;

import entities.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue(value = "FSh")
public class FiftyShades extends BasicShampoo {
    private static final String BRAND="Fifty Shades";
    private static final BigDecimal PRICE=new BigDecimal(6.69);
    private static final Size SIZE=Size.SMALL;

    public FiftyShades() {
    }

    public FiftyShades(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
    }
}
