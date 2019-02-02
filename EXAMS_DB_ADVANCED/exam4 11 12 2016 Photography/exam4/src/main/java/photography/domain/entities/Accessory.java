package photography.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "accessories")
public class Accessory extends BaseEntity{
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "photographer_id", referencedColumnName = "id")
    private Photographer owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }
}
