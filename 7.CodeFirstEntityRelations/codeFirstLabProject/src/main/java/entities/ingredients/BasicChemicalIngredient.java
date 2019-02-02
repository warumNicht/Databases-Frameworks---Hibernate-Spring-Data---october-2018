package entities.ingredients;

import interfaces.ChemicalIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {
    @Column(name="chemical_formula")
    private String chemicalFormula;

    protected BasicChemicalIngredient() {
        super();
    }

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }

    @Override
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula=chemicalFormula;
    }
}
