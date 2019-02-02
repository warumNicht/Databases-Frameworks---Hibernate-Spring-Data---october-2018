package cars.domain.dto;

import com.google.gson.JsonArray;
import java.util.Date;

public class OrderedCustomerDto {
    private Integer Id;
    private String Name;
    private Date BirthDate;
    private boolean IsYoungDriver;
    private JsonArray Sales;

    public OrderedCustomerDto() {
        this.Sales=new JsonArray();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public JsonArray getSales() {
        return Sales;
    }

    public void setSales(JsonArray sales) {
        Sales = sales;
    }
}
