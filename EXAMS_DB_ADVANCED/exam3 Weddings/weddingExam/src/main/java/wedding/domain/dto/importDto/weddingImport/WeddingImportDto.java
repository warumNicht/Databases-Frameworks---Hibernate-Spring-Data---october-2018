package wedding.domain.dto.importDto.weddingImport;


import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class WeddingImportDto {
    @NotNull
    @SerializedName(value = "Bride")
    private String bride;

    @NotNull
    @SerializedName(value = "Bridegroom")
    private String bridegroom;

    @NotNull
    @SerializedName(value = "Date")
    private Date date;

    @NotNull
    @SerializedName(value = "Agency")
    private String agency;

    @SerializedName(value = "Guests")
    private GuestDto[] guests;

    public String getBride() {
        return bride;
    }

    public void setBride(String bride) {
        this.bride = bride;
    }

    public String getBridegroom() {
        return bridegroom;
    }

    public void setBridegroom(String bridegroom) {
        this.bridegroom = bridegroom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public GuestDto[] getGuests() {
        return guests;
    }

    public void setGuests(GuestDto[] guests) {
        this.guests = guests;
    }
}
