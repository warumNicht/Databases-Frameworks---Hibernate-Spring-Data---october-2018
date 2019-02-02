package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.Invitation;

import java.util.Optional;

@Repository
public interface InvitationRepo extends JpaRepository<Invitation,Integer> {
    Optional<Invitation> findById(Integer id);
}
