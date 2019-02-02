package wedding.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
@Table(name = "weddings")
public class Wedding extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "bride_id",
            referencedColumnName = "id",nullable = false)
    private Person bride;
    @OneToOne
    @JoinColumn(name = "bridegroom_id",
            referencedColumnName = "id",nullable = false)
    private Person bridegroom;
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "agency_id",referencedColumnName = "id")
    private Agency agency;

    @OneToMany(mappedBy = "wedding",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Invitation> invitations;

    @ManyToMany
    @JoinTable(name = "weddings_venues",
    joinColumns = @JoinColumn(name = "wedding_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "venue_id",referencedColumnName = "id"))
    private Set<Venue> venues;

//    @ManyToMany
//    @JoinTable(name = "weddings_guests",
//    joinColumns = @JoinColumn(name = "wedding_id",referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "guest_id" ,referencedColumnName = "id"))
//    private Set<Person> guests;

    public Person getBride() {
        return bride;
    }

    public void setBride(Person bride) {
        this.bride = bride;
    }

    public Person getBridegroom() {
        return bridegroom;
    }

    public void setBridegroom(Person bridegroom) {
        this.bridegroom = bridegroom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

//    public Set<Person> getGuests() {
//        return guests;
//    }
//
//    public void setGuests(Set<Person> guests) {
//        this.guests = guests;
//    }


    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }

    public Set<Venue> getVenues() {
        return venues;
    }

    public void setVenues(Set<Venue> venues) {
        this.venues = venues;
    }
}
