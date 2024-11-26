package barisla.example.prova.mappers;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.presentation.model.UserContact;
import barisla.example.presentation.model.Utente;
import barisla.example.prova.integrations.model.UserContactDAO;
import barisla.example.prova.integrations.model.UserContactEntity;
import barisla.example.prova.integrations.model.UtenteEntity;
import barisla.example.prova.services.models.ContattoUtente;
import barisla.example.prova.services.models.CreaContattoUtente;
import barisla.example.prova.services.models.CreaUtente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel="spring")
public interface UtenteMapper {
   // @Mapping(target = "contatti", ignore = true)
    public CreaUtente transform (CreaUtenteRequest nuovoUtente);

    public Utente transform (barisla.example.prova.services.models.Utente utente);

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "contattiUtente", source = "contactEntity")
    public barisla.example.prova.services.models.Utente transform (UtenteEntity utenti);

    @Named("uuidToString")
    default String uuidToString(UUID id){
        return id.toString();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactEntity", ignore = true)
    public UtenteEntity transform (CreaUtente nuovoUtente);

    public List<barisla.example.prova.services.models.Utente> transformListUtenteDAO (List<UtenteEntity> utenti);

    public List<barisla.example.presentation.model.Utente> transformListUtenteService (List<barisla.example.prova.services.models.Utente> utenti);

    public ContattoUtente transformContatto (UserContactEntity contattoEntity);

    public UserContactEntity transformContattoEntity (ContattoUtente contattoUtente);

    public UserContact transformContattoPresentation (ContattoUtente contattoUtente);

    public CreaContattoUtente transformContattoRequest (UserContact insertContattoPerUtenteRequest);
}
