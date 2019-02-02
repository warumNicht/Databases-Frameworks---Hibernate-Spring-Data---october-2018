package judge.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MaxResultForProblem extends BaseEntity{
    @ManyToOne
    private User user;

    @ManyToOne
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "submission_id",referencedColumnName = "id")
    private Submission submission;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
