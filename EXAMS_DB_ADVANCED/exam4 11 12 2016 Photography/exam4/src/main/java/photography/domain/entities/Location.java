package photography.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location extends BaseEntity{
    @Column
    private String name;

    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER)
    private Set<Workshop> workshops;

    public Set<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Set<Workshop> workshops) {
        this.workshops = workshops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
