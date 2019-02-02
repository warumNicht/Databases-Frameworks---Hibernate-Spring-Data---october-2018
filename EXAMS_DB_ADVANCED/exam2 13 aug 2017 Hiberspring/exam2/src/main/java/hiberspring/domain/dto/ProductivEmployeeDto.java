package hiberspring.domain.dto;

import com.google.gson.annotations.SerializedName;

public class ProductivEmployeeDto {
    @SerializedName(value = "full_name")
    private String fullName;
    private String position;
    @SerializedName(value = "number")
    private String card;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
