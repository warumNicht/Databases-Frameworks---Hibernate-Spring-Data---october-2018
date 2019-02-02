package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race extends BaseEntity{
    @Column(nullable = false,columnDefinition = " INT(11) DEFAULT 0 ")
    private Integer laps;

    @ManyToOne
    @JoinColumn(name = "district_id",
            referencedColumnName = "id",nullable = false)
    private District district;

    @OneToMany(mappedBy = "race")
    private Set<RaceEntry> entries;

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
