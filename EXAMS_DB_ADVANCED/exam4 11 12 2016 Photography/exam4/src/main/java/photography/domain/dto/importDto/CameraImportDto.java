package photography.domain.dto.importDto;

import com.google.gson.annotations.SerializedName;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CameraImportDto {
    @NotNull
    private String type;
    @NotNull
    private String make;
    @NotNull
    private String model;
    private Boolean isFullFrame;

    @Nullable
    @Min(value = 100)
    private Integer minISO;
    private Integer maxISO;

    @SerializedName(value = "MaxShutterSpeed")
    private Integer maxShutterSpeed;

    private String maxVideoResolution;
    private Integer maxFrameRate;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
