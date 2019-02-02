package pb2_carsDealer.domain.dto.seedDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierSeedDto {

    @XmlAttribute(name = "name")
    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!" )
    private String name;

    @XmlAttribute(name = "is-importer")
    @NotNull(message = "Importer cannot be null!")
    private Boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
