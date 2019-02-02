package judge.domain.dtos.contestImportDto;

public class ContestImportDto {
    private Integer id;
    private String name;
    private CategoryContestDto category;
    private StrategyContestDto[] allowedStrategies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryContestDto getCategory() {
        return category;
    }

    public void setCategory(CategoryContestDto category) {
        this.category = category;
    }

    public StrategyContestDto[] getAllowedStrategies() {
        return allowedStrategies;
    }

    public void setAllowedStrategies(StrategyContestDto[] allowedStrategies) {
        this.allowedStrategies = allowedStrategies;
    }
}
