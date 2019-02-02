package wedding.domain.dto.query2;

public class WeddingExportDto {
    private String bride;
    private String bridegroom;
    private AgencyGuestDto agency;
    private Long invitedGuests;
    private Long brideGuests;
    private Long bridegroomGuests;
    private Long attendingGuests;
    private GuestExportDto[] guests;

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

    public AgencyGuestDto getAgency() {
        return agency;
    }

    public void setAgency(AgencyGuestDto agency) {
        this.agency = agency;
    }

    public Long getInvitedGuests() {
        return invitedGuests;
    }

    public void setInvitedGuests(Long invitedGuests) {
        this.invitedGuests = invitedGuests;
    }

    public Long getBrideGuests() {
        return brideGuests;
    }

    public void setBrideGuests(Long brideGuests) {
        this.brideGuests = brideGuests;
    }

    public Long getBridegroomGuests() {
        return bridegroomGuests;
    }

    public void setBridegroomGuests(Long bridegroomGuests) {
        this.bridegroomGuests = bridegroomGuests;
    }

    public Long getAttendingGuests() {
        return attendingGuests;
    }

    public void setAttendingGuests(Long attendingGuests) {
        this.attendingGuests = attendingGuests;
    }

    public GuestExportDto[] getGuests() {
        return guests;
    }

    public void setGuests(GuestExportDto[] guests) {
        this.guests = guests;
    }
}
