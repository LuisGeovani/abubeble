package br.ufac.sgcmapi.profissional;

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

@WebMvcTest(ProfissionalController.class)
public class ProfissionalControllerTest {

    @MockitoBean
    private ProfissionalService servico;

    @Autowired
    private MockMvc mockMvc;

    private Profissional profissional;
    private List<Profissional> profissionaisLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        profissional = new Profissional();
        profissional.setId(1L);

        profissionaisLista = List.of(profissional); 

        conteudoJson = new ObjectMapper().writeValueAsString(profissional);
    }

    @Test
    void testProfissionalInserir() throws Exception {
        Mockito.when(servico.salvar(any(Profissional.class)))
               .thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.post("/profissional/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testProfisionalConsultar() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(profissionaisLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/profissional/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testProfissionalAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Profissional.class))).thenReturn(profissional);

        mockMvc.perform(MockMvcRequestBuilders.put("/profissional/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testProfissionalRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/profissional/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}