package pb5_BillsPaymentSystem.entities.details;

import pb5_BillsPaymentSystem.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class BankAccount extends BillingDetail {
    @Column(name = "name")
    private String name;

    @Column(name = "swift")
    private String swift;

    public BankAccount() {
        super();
    }

    public BankAccount(String number, User owner, String name, String swift) {
        super(number, owner);
        this.name = name;
        this.swift = swift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
