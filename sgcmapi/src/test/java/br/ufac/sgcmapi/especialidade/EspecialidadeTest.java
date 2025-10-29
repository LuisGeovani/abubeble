package br.ufac.sgcmapi.especialidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadeTest {
    
    private Especialidade especialidade;

    @BeforeEach
    void setUp(){
        especialidade = new Especialidade();
    }

    @Test
    void TestEspecialidadeGettersSetters(){
        var id = 2L;
        var nome = "Especialidade Teste";
    
        especialidade.setId(id);
        especialidade.setNome(nome);

        assertEquals(2L, especialidade.getId());
        assertEquals("Especialidade Teste", especialidade.getNome());
    }
}