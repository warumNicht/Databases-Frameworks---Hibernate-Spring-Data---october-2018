package judge.domain.entities;

import javax.persistence.*;
@Entity(name = "strategies")
public class Strategy extends BaseEntity {
    @Column
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
