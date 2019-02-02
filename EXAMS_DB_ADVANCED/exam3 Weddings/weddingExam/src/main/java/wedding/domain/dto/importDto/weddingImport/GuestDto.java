package wedding.domain.dto.importDto.weddingImport;

import com.google.gson.annotations.SerializedName;

public class GuestDto {
    @SerializedName(value = "Name")
    private String name;
    @SerializedName(value = "RSVP")
    private Boolean isPresent;
    @SerializedName(value = "Family")
    private String family;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
