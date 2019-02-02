package cars.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseWithName {
    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier",fetch = FetchType.EAGER)
    private Set<Part> parts;

    public Supplier() {
        super();
        this.parts=new HashSet<>();
    }

    public Supplier(String name) {
        super(name);
        this.parts=new HashSet<>();
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
