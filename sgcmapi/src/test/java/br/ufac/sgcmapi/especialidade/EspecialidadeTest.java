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
    void TestEspecialidadeGetSet(){
        var id = 1025l;
        var nome = "Especialidade";

        especialidade.setId(id);
        especialidade.setNome(nome);

        assertEquals(1025l, especialidade.getId());
        assertEquals("Especialidade", especialidade.getNome());
    }
}
