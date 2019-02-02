package wedding.domain.entities;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "agencies")
public class Agency extends BaseEntity{
    @Column
    private String name;
    @Column(name = "employees_count")
    private Integer employeeCount;
    @Column
    private String town;
    @OneToMany(mappedBy = "agency",fetch = FetchType.EAGER)
    private Set<Wedding> weddings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Set<Wedding> getWeddings() {
        return weddings;
    }

    public void setWeddings(Set<Wedding> weddings) {
        this.weddings = weddings;
    }
}
