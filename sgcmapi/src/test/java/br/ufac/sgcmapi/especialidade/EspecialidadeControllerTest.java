package br.ufac.sgcmapi.especialidade;

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

@WebMvcTest(EspecialidadeController.class)
public class EspecialidadeControllerTest {

    @MockitoBean
    private EspecialidadeService servico;

    @Autowired
    private MockMvc mockMvc;

    private Especialidade especialidade;
    private List<Especialidade> especialidadesLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        especialidade = new Especialidade();
        especialidade.setId(1L);

        especialidadesLista = List.of(especialidade); 

        conteudoJson = new ObjectMapper().writeValueAsString(especialidade);
    }

    @Test
    void testEspecialidadeInserir() throws Exception {
        Mockito.when(servico.salvar(any(Especialidade.class)))
               .thenReturn(especialidade);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/especialidade/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testEspecialidadeConsultar() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(especialidadesLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/especialidade/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testEspecialidadeAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Especialidade.class))).thenReturn(especialidade);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/especialidade/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testEspecialidadeRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/especialidade/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}