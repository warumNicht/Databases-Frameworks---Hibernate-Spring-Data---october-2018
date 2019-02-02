package judge.domain.entities;

import javax.persistence.*;
import java.util.Set;
@Entity(name = "problems")
public class Problem extends BaseEntity {
    @Column
    private String name;

    @OneToMany(mappedBy = "problem")
    private Set<Submission> submissions;

    @ManyToMany
    @JoinTable(name = "problems_users",
    joinColumns = @JoinColumn(name = "problem_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    private Set<User> contestants;

    @ManyToOne
    private Contest contest;

    @OneToMany(mappedBy = "problem")
    private Set<MaxResultForProblem> maxResultForProblems;

    @OneToMany(mappedBy = "problem")
    private Set<Test> tests;

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

    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Set<MaxResultForProblem> getMaxResultForProblems() {
        return maxResultForProblems;
    }

    public void setMaxResultForProblems(Set<MaxResultForProblem> maxResultForProblems) {
        this.maxResultForProblems = maxResultForProblems;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
