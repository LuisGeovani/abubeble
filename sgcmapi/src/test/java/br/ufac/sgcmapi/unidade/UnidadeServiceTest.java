package br.ufac.sgcmapi.unidade;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UnidadeServiceTest {

    @Mock
    private UnidadeRepository repo;

    @InjectMocks
    private UnidadeService servico;

    private Unidade unidade;
    private List<Unidade> unidadesLista;

    @BeforeEach
    void setUp(){
        unidade = new Unidade();
        unidade.setId(1L);

        unidadesLista = List.of(unidade);
    }

    @Test
    void testUnidadeConsultar(){
        Mockito.when(repo.consultar((String) null))
               .thenReturn(unidadesLista);
        var resultado = servico.consultar((String) null);
        assertEquals(unidadesLista, resultado);
    }

    @Test
    void testUnidadeConsultarPorId() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(unidade));
        var resultado = servico.consultar(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testUnidadeSalvar() {
        Mockito.when(repo.save(unidade)).thenReturn(unidade);
        var resultado = servico.salvar(unidade);
        assertEquals(unidade, resultado);
    }

    @Test
    void testUnidadeRemover() {
        doNothing().when(repo).deleteById(1L);
        servico.remover(1L);
        Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
    }
}