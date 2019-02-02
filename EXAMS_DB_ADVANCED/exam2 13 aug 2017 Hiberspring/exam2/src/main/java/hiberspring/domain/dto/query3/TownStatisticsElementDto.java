package hiberspring.domain.dto.query3;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownStatisticsElementDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer population;
    @XmlAttribute(name = "town_clients")
    private long clients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


    public long getClients() {
        return clients;
    }

    public void setClients(long clients) {
        this.clients = clients;
    }
}
