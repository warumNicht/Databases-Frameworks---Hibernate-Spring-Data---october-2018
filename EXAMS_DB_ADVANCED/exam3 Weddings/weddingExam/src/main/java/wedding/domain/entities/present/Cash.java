package wedding.domain.entities.present;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cash extends Present{
    @Column()
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
