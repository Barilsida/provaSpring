package barisla.example.prova.controllers;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.prova.expetions.UtenteEsisteGia;
import barisla.example.prova.mappers.UtenteMapper;
import barisla.example.prova.mappers.UtenteMapperImpl;
import barisla.example.prova.services.UserService;
import barisla.example.prova.services.models.CreaUtente;
import barisla.example.prova.services.models.Utente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@WebMvcTest(controllers = UtentiController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ComponentScan("barisla.example.prova.mappers")
class UtentiControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtentiController utentiController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(utentiController).build();
    }


    @Test
    void creaUtenteSuccesso() {



        CreaUtente richiestaNuovoUtenteService = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                email("mrossi@gmail.com").
                cellulare("12312345").
                eta(23).build();


        CreaUtenteRequest richiestaNuovoUtenteController= new CreaUtenteRequest();
        richiestaNuovoUtenteController.setNome(richiestaNuovoUtenteService.getNome());
        richiestaNuovoUtenteController.setCognome(richiestaNuovoUtenteService.getCognome());
        richiestaNuovoUtenteController.setCellulare(richiestaNuovoUtenteService.getCellulare());
        richiestaNuovoUtenteController.setEmail(richiestaNuovoUtenteService.getEmail());
        richiestaNuovoUtenteController.setEta(richiestaNuovoUtenteService.getEta());

        Utente utenteCreato = new Utente();
        utenteCreato.setId("1231232");
        utenteCreato.setNome(richiestaNuovoUtenteService.getNome());
        utenteCreato.setCognome(richiestaNuovoUtenteService.getCognome());
        utenteCreato.setCellulare(richiestaNuovoUtenteService.getCellulare());
        utenteCreato.setEmail(richiestaNuovoUtenteService.getEmail());
        utenteCreato.setEta(richiestaNuovoUtenteService.getEta());


        try {

            //when(utenteMapper.transform(richiestaNuovoUtenteController)).thenReturn(richiestaNuovoUtenteService);
            when(userService.creaUtente(ArgumentMatchers.any())).thenReturn(utenteCreato);

            ObjectMapper objectMapper = new ObjectMapper();
            String bodyAsString = objectMapper.writeValueAsString(richiestaNuovoUtenteController);


            mockMvc.perform(post("/api/v1/utenti")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bodyAsString))
                            .andExpect(status().isCreated())
                    .andExpect(jsonPath(
                            "$.id"
                    ).value(
                            "1231232"
                    ))
                    .andExpect(jsonPath(
                            "$.nome"
                    ).value(
                            "Paolo"
                    ))
                    .andExpect(jsonPath(
                            "$.cognome"
                    ).value(
                            "verdi"
                    ))
                    .andExpect(jsonPath(
                            "$.email"
                    ).value(
                            "mrossi@gmail.com"
                    ))
                    .andExpect(jsonPath(
                            "$.cellulare"
                    ).value(
                            "12312345"
                    ))
                    .andExpect(jsonPath(
                                    "$.eta"
                            ).value(
                                    23
                            )
                    );;


        } catch (UtenteEsisteGia u) {
            assertEquals(true, true);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}