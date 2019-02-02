package pb2_carsDealer.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllLocalSuppliersDto {
    @XmlElement(name = "supplier")
    private List<LocalSupplierDto> localSupplierDtos;

    public List<LocalSupplierDto> getLocalSupplierDtos() {
        return localSupplierDtos;
    }

    public void setLocalSupplierDtos(List<LocalSupplierDto> localSupplierDtos) {
        this.localSupplierDtos = localSupplierDtos;
    }
}
