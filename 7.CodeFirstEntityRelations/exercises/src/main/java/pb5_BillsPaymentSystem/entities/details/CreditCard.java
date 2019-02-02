package pb5_BillsPaymentSystem.entities.details;

import pb5_BillsPaymentSystem.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {
    @Column(name = "type")
    private String type;

    @Column(name = "expiration_month")
    private String expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

    public CreditCard() {
        super();
    }

    public CreditCard(String number, User owner, String type, String expirationMonth, int expirationYear) {
        super(number, owner);
        this.type = type;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
