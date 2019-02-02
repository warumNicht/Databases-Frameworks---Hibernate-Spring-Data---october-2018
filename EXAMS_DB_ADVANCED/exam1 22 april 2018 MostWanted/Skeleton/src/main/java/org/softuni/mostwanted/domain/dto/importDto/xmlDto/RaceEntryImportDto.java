package org.softuni.mostwanted.domain.dto.importDto.xmlDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryImportDto {

    @XmlAttribute(name = "has-finished")
    private boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private double finishedTime;
    @XmlAttribute(name = "car-id")
    private Integer carId;

    @XmlElement(name = "racerName")
    private String racer;

    public RaceEntryImportDto() {
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public double getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(double finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRacer() {
        return racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
