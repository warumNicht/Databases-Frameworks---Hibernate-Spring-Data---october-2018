package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@DiscriminatorValue(value = "MiT")
public class Mint extends BasicIngredient {
    private static final String NAME="Mint";
    private static final BigDecimal PRICE=new BigDecimal(3.53);

    public Mint() {
        super(NAME, PRICE);
    }
}
