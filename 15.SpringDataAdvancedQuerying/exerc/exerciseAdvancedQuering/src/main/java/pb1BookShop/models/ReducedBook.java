package pb1BookShop.models;

import java.math.BigDecimal;

public interface ReducedBook {
    String getTitle();

    EditionType getEditionType();

    AgeRestriction getAgeRestriction();

    BigDecimal getPrice();


}
