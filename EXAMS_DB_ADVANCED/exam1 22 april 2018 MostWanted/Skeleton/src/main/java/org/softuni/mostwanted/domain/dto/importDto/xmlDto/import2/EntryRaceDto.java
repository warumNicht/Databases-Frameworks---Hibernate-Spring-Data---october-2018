package org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EntryRaceDto {
    @XmlAttribute
    private Integer id;

    public EntryRaceDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
