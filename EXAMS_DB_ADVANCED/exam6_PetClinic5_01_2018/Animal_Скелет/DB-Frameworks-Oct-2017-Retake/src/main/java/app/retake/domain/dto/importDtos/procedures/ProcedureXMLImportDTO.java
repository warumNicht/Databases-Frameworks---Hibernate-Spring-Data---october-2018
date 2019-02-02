package app.retake.domain.dto.importDtos.procedures;

import com.sun.istack.Nullable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {
    @XmlElement
    @Nullable
    @Size(min = 3,max = 40)
    private String vet;

    @XmlElement
    @Nullable
    @Pattern(regexp = "^.{7}[0-9]{3}$")
    private String animal;

    @XmlElement(name = "animal-aids")
    private AnimalCollectionDto animalCollectionDto;

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public AnimalCollectionDto getAnimalCollectionDto() {
        return animalCollectionDto;
    }

    public void setAnimalCollectionDto(AnimalCollectionDto animalCollectionDto) {
        this.animalCollectionDto = animalCollectionDto;
    }
}
