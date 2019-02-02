package judge.repositories;

import judge.domain.entities.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest,Long> {
}
