package pb1_GringottsDatabase;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
@PersistenceContext(unitName = "gringotts")
@Entity
@Table(name = "wizard_deposits")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class WizardDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition=" INT(11) UNSIGNED ")
    private int id;

    @Column(name = "first_name",length = 50)
    private String firstName;

    @Column(name = "last_name",length = 60,nullable = false)
    private String lastName;

    @Column(name = "notes",length = 1000)
    private String notes;

    @Min(value = 1, message = "The value must be positive")
    @Column(name = "age",nullable = false,columnDefinition=" SMALLINT(6) UNSIGNED ")
    private int age;

    @Column(name = "magic_wand_creator",length = 100)
    private String magicWandCreator;

    @Min(value = 1, message = "The value must be positive")
    @Column(name = "magic_wand_size",columnDefinition=" SMALLINT(6) UNSIGNED ")
    private short magicWandSize;

    @Column(name = "deposit_group",length = 20)
    private String depositGroup;

    @Column(name = "deposit_start_date")
    private Date depositStartDate;

    @Column(name = "deposit_amount")
    private double depositAmount;

    @Column(name = "deposit_interest")
    private double depositInterest;

    @Column(name = "deposit_charge")
    private double depositCharge;

    @Column(name = "deposit_expiration_date")
    private Date depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public WizardDeposit() {
    }

    public WizardDeposit(String firstName, String lastName, String notes,
                         int age, String magicWandCreator, short magicWandSize,
                         String depositGroup, Date depositStartDate, double depositAmount,
                         double depositInterest, double depositCharge, Date depositExpirationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = depositStartDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpirationDate = depositExpirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public short getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public Date getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public double getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public Date getDepositExpirationDate() {
        return depositExpirationDate;
    }

    public void setDepositExpirationDate(Date depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
