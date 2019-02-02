package photography.domain.dto.importDto;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Digits;

public class LensImportDto {
    private String make;
    private Integer focalLength;

    @Digits(integer = 5,fraction = 1)
    private Double maxAperture;

    private String compatibleWith;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public Double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(Double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }
}
