package photography.domain.entities.cameras;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DSLR_Cameras")
public class DSLRCamera extends Camera {
    @Column(name = "max_shutter_speed")
    private Integer maxShutterSpeed;

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
