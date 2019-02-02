package mostwanted.domain.dtos.exportDto.query1;

public class TownExportDto {
    private  String name;
    private Long racers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRacers() {
        return racers;
    }

    public void setRacers(Long racers) {
        this.racers = racers;
    }
}
