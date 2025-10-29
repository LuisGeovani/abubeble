    package br.ufac.sgcmapi.atendimento;

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

    @WebMvcTest(AtendimentoController.class)
    public class AtendimentoControllerTest {

        @MockitoBean
        private AtendimentoService servico;

        @Autowired
        private MockMvc mockMvc;

        private Atendimento atendimento;
        private List<Atendimento> atendimentosLista;
        private String conteudoJson;

        @BeforeEach
        void setUp() throws JsonProcessingException {
            atendimento = new Atendimento();
            atendimento.setId(1L);
            atendimento.setStatus(EStatus.CONFIRMADO);

            atendimentosLista = List.of(atendimento);

            conteudoJson = new ObjectMapper().findAndRegisterModules().writeValueAsString(atendimento);
        }

        @Test
        void testAtendimentoInserir() throws Exception {
            Mockito.when(servico.salvar(any(Atendimento.class)))
                .thenReturn(atendimento);

            mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/inserir")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(conteudoJson))
                    
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
        }

        @Test
        void testAtendimentoCOnsultar() throws Exception {
            Mockito.when(servico.consultar(null, null)).thenReturn(atendimentosLista);

            mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/consultar"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
        }

        @Test
        void testAtendimentoConsultarPorId() throws Exception {
            Mockito.when(servico.consultar(1L)).thenReturn(atendimento);

            mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/consultar/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
        }

        @Test
        void testAtendimentoAtualizar() throws Exception {
            Mockito.when(servico.salvar(any(Atendimento.class))).thenReturn(atendimento);

            mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/atualizar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(conteudoJson))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testAtendimentoRemover() throws Exception {
            doNothing().when(servico).remover(1L);

            mockMvc.perform(MockMvcRequestBuilders.delete("/atendimento/remover/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testAtualizarStatus() throws Exception {
            atendimento.setStatus(EStatus.CHEGADA); 

            Mockito.when(servico.atualizarStatus(1L)).thenReturn(atendimento);

            mockMvc.perform(MockMvcRequestBuilders.put("/atendimento/status/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("CHEGADA")));
        }
    }