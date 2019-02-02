package judge.domain.entities;

import javax.persistence.*;
import java.util.Set;
@Entity(name = "contests")
public class Contest extends BaseEntity{
    @Column
    private String name;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "contest")
    private Set<Problem> problems;

    @ManyToMany
    @JoinTable(name = "contests_users",
    joinColumns = @JoinColumn(name = "contest_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    private Set<User> contestants;

    @OneToMany(mappedBy = "contest")
    private Set<MaxResultForContest> maxResultForContests;

    @ManyToMany
    @JoinTable(name = "contests_strategies",
    joinColumns = @JoinColumn(name = "contest_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "strategy_id",referencedColumnName = "id"))
    private Set<Strategy> strategies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    public Set<MaxResultForContest> getMaxResultForContests() {
        return maxResultForContests;
    }

    public void setMaxResultForContests(Set<MaxResultForContest> maxResultForContests) {
        this.maxResultForContests = maxResultForContests;
    }

    public Set<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<Strategy> strategies) {
        this.strategies = strategies;
    }
}
