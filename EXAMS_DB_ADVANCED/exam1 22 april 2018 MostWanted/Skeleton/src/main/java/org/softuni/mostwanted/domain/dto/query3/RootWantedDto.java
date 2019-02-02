package org.softuni.mostwanted.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RootWantedDto {
    @XmlElement(name = "racer")
    private WanterRacerDto wanterRacerDto;

    public RootWantedDto() {
    }

    public WanterRacerDto getWanterRacerDto() {
        return wanterRacerDto;
    }

    public void setWanterRacerDto(WanterRacerDto wanterRacerDto) {
        this.wanterRacerDto = wanterRacerDto;
    }
}
