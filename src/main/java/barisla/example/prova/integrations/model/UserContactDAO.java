package barisla.example.prova.integrations.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserContactDAO extends JpaRepository<UserContactEntity, UUID> {

}
