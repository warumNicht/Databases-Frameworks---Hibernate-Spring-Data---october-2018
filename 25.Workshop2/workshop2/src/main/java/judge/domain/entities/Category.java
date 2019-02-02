package judge.domain.entities;

import javax.persistence.*;
import java.util.Set;
@Entity(name = "categories")
public class Category extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> subcategories;


   @OneToMany(mappedBy = "category")
    private Set<Contest> contests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}
