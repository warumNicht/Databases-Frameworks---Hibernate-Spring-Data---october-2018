package hiberspring.domain.dto.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "branches")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class BranchStatRootDto {
    @XmlElement(name = "branch")
    private List<BranchStatElDto> branchStatElDtos;

    public List<BranchStatElDto> getBranchStatElDtos() {
        return branchStatElDtos;
    }

    public void setBranchStatElDtos(List<BranchStatElDto> branchStatElDtos) {
        this.branchStatElDtos = branchStatElDtos;
    }
}
