package pb1BookShop.models;

import java.math.BigDecimal;

public class ReducedBookImpl implements ReducedBook{

    private String title;
    private AgeRestriction ageRestriction;
    private EditionType editionType;
    private BigDecimal price;

    public ReducedBookImpl(String title, AgeRestriction ageRestriction, EditionType editionType, BigDecimal price) {
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.editionType = editionType;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public EditionType getEditionType() {
        return this.editionType;
    }

    @Override
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }
}
