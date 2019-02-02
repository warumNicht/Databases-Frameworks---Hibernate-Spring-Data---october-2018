package org.softuni.mostwanted.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EntryDto {
    @XmlElement(name = "finish-time")
    private Double finishedTime;
    @XmlElement(name = "car")
    private String car;

    public EntryDto() {
    }

    public Double getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Double finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
