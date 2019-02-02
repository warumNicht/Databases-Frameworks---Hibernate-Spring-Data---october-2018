package pb2_carsDealer.domain.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierRootDto {
    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> supplierSeedDtos;

    public List<SupplierSeedDto> getSupplierSeedDtos() {
        return supplierSeedDtos;
    }

    public void setSupplierSeedDtos(List<SupplierSeedDto> supplierSeedDtos) {
        this.supplierSeedDtos = supplierSeedDtos;
    }
}
