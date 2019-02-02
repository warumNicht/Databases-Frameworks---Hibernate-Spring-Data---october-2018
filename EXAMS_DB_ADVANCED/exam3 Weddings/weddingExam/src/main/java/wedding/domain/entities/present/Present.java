package wedding.domain.entities.present;

import wedding.domain.entities.BaseEntity;
import wedding.domain.entities.Invitation;
import wedding.domain.entities.Person;

import javax.persistence.*;
@Entity
@Table(name = "presents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Present extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

//    @OneToOne
//    @JoinColumn(name = "invitation_id", referencedColumnName = "id")
//    private Invitation invitation;

//    public Invitation getInvitation() {
//        return invitation;
//    }
//
//    public void setInvitation(Invitation invitation) {
//        this.invitation = invitation;
//    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
