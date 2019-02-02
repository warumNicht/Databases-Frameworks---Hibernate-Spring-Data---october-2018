package judge.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;
@Entity(name = "users")
public class User extends BaseEntity {
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Submission> submissions;

    @OneToMany(mappedBy = "user")
    private Set<MaxResultForContest> maxResultForContests;

    @OneToMany(mappedBy = "user")
    private Set<MaxResultForProblem> maxResultForProblems;

    @ManyToMany(mappedBy = "contestants")
    private Set<Contest> contests;

    @ManyToMany(mappedBy = "contestants")
    private Set<Problem> problems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public Set<MaxResultForContest> getMaxResultForContests() {
        return maxResultForContests;
    }

    public void setMaxResultForContests(Set<MaxResultForContest> maxResultForContests) {
        this.maxResultForContests = maxResultForContests;
    }

    public Set<MaxResultForProblem> getMaxResultForProblems() {
        return maxResultForProblems;
    }

    public void setMaxResultForProblems(Set<MaxResultForProblem> maxResultForProblems) {
        this.maxResultForProblems = maxResultForProblems;
    }

    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
