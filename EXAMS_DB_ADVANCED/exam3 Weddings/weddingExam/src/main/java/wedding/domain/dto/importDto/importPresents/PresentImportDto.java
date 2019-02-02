package wedding.domain.dto.importDto.importPresents;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "present")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PresentImportDto {
    @NotNull
    @XmlAttribute
    private String type;

    @NotNull
    @XmlAttribute(name = "invitation-id")
    private Integer invitationId;

    @XmlAttribute
    private Double amount;

    @XmlAttribute(name = "present-name")
    private String presentName;

    @XmlAttribute
    @Pattern(regexp = "^Small$|^Medium$|^Large$|^NotSpecified$")
    private String size;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
