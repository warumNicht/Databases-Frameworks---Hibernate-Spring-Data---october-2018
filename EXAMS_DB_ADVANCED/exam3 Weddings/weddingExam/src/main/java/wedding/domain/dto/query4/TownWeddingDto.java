package wedding.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "wedding")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownWeddingDto {
    @XmlAttribute
    private long cash;
    @XmlAttribute
    private Integer presents;
    @XmlElement
    private String bride;
    @XmlElement
    private String bridegroom;

    @XmlElementWrapper(name = "guests")
    @XmlElement(name = "guest")
    private List<TownGuestDto> guests;

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public Integer getPresents() {
        return presents;
    }

    public void setPresents(Integer presents) {
        this.presents = presents;
    }

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

    public List<TownGuestDto> getGuests() {
        return guests;
    }

    public void setGuests(List<TownGuestDto> guests) {
        this.guests = guests;
    }
}
