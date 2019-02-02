package wedding.domain.entities.present;

import wedding.domain.entities.enums.Size;

import javax.persistence.*;

@Entity
public class Gift  extends Present{
    @Column()
    private String name;
    @Column()
    @Enumerated(value = EnumType.STRING)
    private Size size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
