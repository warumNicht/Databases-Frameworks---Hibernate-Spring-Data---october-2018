package pb5_BillsPaymentSystem.entities.details;

import pb5_BillsPaymentSystem.entities.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail {

    @Id
    @Column(name = "number")
    private String number;

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    protected BillingDetail() {
    }

    protected BillingDetail(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
