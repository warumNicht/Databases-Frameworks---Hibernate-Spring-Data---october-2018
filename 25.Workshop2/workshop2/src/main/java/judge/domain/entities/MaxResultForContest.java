package judge.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "max_result_for_contests")
public class MaxResultForContest extends BaseEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Contest contest;

    @Column(name = "average_performance")
    private Double averagePerformance;

    @Column(name = "total_points")
    private Double totalPoints;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Double getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(Double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
}
