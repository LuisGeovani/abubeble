package br.ufac.sgcmapi.convenio;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConvenioServiceMockTest {
    @Mock
    private ConvenioRepository repo;

    @InjectMocks
    private ConvenioService servico;

    private Convenio convenio;
    private List<Convenio> convenios;

    @BeforeEach
    void setUp(){
        convenio = new Convenio();
        convenio.setId(1L);

        var convenio1 = new Convenio();
        convenio1.setId(1L);
        convenio1.setAtivo(false);

        var convenio2 = new Convenio();
        convenio2.setId(2L);
        convenio2.setAtivo(true);

        convenios = new ArrayList<>();
        convenios.add(convenio1);
        convenios.add(convenio2);
    }

    @Test
    void testConvenioColsultarTodos(){
        Mockito.when(repo.consultar(""))
            .thenReturn(convenios);
        var resultado = servico.consultar("");
        assertEquals(2, resultado.size());
    }
}