package barisla.example.prova.integrations.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface UtenteDAO extends JpaRepository<UtenteDAO, UUID> {

    public List<UtenteDAO> findByCognomeIgnoreCase(String cognome);

    public List<UtenteDAO> findByEmail(String email);

    public void deleteById(UUID id);

    @Query("Select u FROM UtenteDAO u WHERE u.email like %:email%")
    public List<UtenteDAO> cercaPerEmail(@Param("email") String email);

}
