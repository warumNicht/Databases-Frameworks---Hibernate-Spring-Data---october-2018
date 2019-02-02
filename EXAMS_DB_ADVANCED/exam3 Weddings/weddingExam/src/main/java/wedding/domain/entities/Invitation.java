package wedding.domain.entities;

import wedding.domain.entities.present.Present;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "wedding_id",
            referencedColumnName = "id",nullable = false)
    private Wedding wedding;
    @OneToOne
    @JoinColumn(name = "guest_id",
            referencedColumnName = "id",nullable = false)
    private Person guest;
    @OneToOne
    @JoinColumn(name = "present_id",referencedColumnName = "id")
    private Present present;
    @Column
    private Boolean attending;
    @Column(nullable = false)
    private String family;

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public Person getGuest() {
        return guest;
    }

    public void setGuest(Person guest) {
        this.guest = guest;
    }

    public Present getPresent() {
        return present;
    }

    public void setPresent(Present present) {
        this.present = present;
    }

    public Boolean getAttending() {
        return attending;
    }

    public void setAttending(Boolean attending) {
        this.attending = attending;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
