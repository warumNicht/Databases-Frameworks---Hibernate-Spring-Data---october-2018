package cars.domain.dto;

public class CarModelDto {
    private int Id;
    private String Make;
    private String Model;
    private long TravelledDistance;

    public CarModelDto() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
