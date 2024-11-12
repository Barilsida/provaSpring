package barisla.example.prova.integrations.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface UtentiDAO extends JpaRepository<UtenteEntity, UUID> {

    public List<UtenteEntity> findByCognomeIgnoreCase(String cognome);

    public List<UtenteEntity> findByEmail(String email);

    public void deleteById(UUID id);

    @Query("Select u FROM UtenteEntity u WHERE u.email like %:email%")
    public List<UtenteEntity> cercaPerEmail(@Param("email") String email);

}
