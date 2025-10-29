package br.ufac.sgcmapi.paciente;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @MockitoBean
    private PacienteService servico;

    @Autowired
    private MockMvc mockMvc;

    private Paciente paciente;
    private List<Paciente> pacientesLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        paciente = new Paciente();
        paciente.setId(1L);

        pacientesLista = List.of(paciente); 

        conteudoJson = new ObjectMapper().writeValueAsString(paciente);
    }

    @Test
    void testPacienteInserir() throws Exception {
        Mockito.when(servico.salvar(any(Paciente.class)))
               .thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testPacienteConsultar() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(pacientesLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testPacienteAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Paciente.class))).thenReturn(paciente);

        mockMvc.perform(MockMvcRequestBuilders.put("/paciente/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPacienteRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}