package photography.domain.entities.cameras;

import photography.domain.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Camera extends BaseEntity {
    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(name = "is_full_frame")
    private Boolean isFullFrame;

    @Column(name = "min_ISO")
    private Integer minISO;

    @Column(name = "max_ISO")
    private Integer maxISO;


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(Boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinISO() {
        return minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }
}
