package wedding.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wedding.domain.dto.importDto.importPresents.PresentImportDto;
import wedding.domain.dto.importDto.importPresents.PresentRootDto;
import wedding.domain.entities.Invitation;
import wedding.domain.entities.enums.Size;
import wedding.domain.entities.present.Cash;
import wedding.domain.entities.present.Gift;
import wedding.repositories.InvitationRepo;
import wedding.repositories.PresentRepo;
import wedding.services.interfaces.PresentService;
import wedding.util.TextUtil;
import wedding.util.ValidationUtil;

@Service
public class PresentServiceImpl implements PresentService {
    private PresentRepo presentRepo;
    private InvitationRepo invitationRepo;
    private ValidationUtil validationUtil;
@Autowired
    public PresentServiceImpl(PresentRepo presentRepo, InvitationRepo invitationRepo, ValidationUtil validationUtil) {
        this.presentRepo = presentRepo;
    this.invitationRepo = invitationRepo;
    this.validationUtil = validationUtil;
    }
    @Override
    public String importPresents(PresentRootDto presentRootDto){
        StringBuilder res=new StringBuilder();

        for (PresentImportDto presentImportDto : presentRootDto.getPresentImportDtos()) {
            if(!this.validationUtil.isValid(presentImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Integer invitationId = presentImportDto.getInvitationId();
            Invitation invitation = this.invitationRepo.findById(invitationId).orElse(null);
            if(invitation==null){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(presentImportDto.getType().equals("cash")){
                if(presentImportDto.getAmount()==null||presentImportDto.getAmount()<=0){
                    res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                Cash  cash=new Cash();
                cash.setAmount(presentImportDto.getAmount());
                cash.setOwner(invitation.getGuest());
//                cash.setInvitation(invitation);
                this.presentRepo.saveAndFlush(cash);
               invitation.setPresent(cash);

                res.append(String.format("Succesfully imported gift from %s",
                        invitation.getGuest().getFullName()))
                        .append(System.lineSeparator());

            }else {
                if(presentImportDto.getPresentName()==null){
                    res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                Gift gift=new Gift();
                gift.setOwner(invitation.getGuest());
                gift.setName(presentImportDto.getPresentName());
                if(presentImportDto.getSize()==null){
                    gift.setSize(Size.NotSpecified);
                }else {
                    gift.setSize(Size.valueOf(presentImportDto.getSize()));
                }
//                gift.setInvitation(invitation);
                this.presentRepo.saveAndFlush(gift);
                invitation.setPresent(gift);


                res.append(String.format("Succesfully imported gift from %s",
                        invitation.getGuest().getFullName()))
                        .append(System.lineSeparator());

            }
            this.invitationRepo.save(invitation);

        }
        return res.toString().trim();
    }
}
