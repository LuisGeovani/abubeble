package br.ufac.sgcmapi.atendimento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AtendimentoRepositoryIntegracaoTest {
    @Autowired
    private AtendimentoRepository repo;

    @Test
    void testAtendimentoConsultarPorProfisisonal() {
        var termoBusca = "Maria";
        var atendimentos = repo.consultar(termoBusca, null);
        assertEquals(2, atendimentos.size());
        var nomeProfissional = atendimentos.get(0).getProfissional().getNome();
        assertTrue(nomeProfissional.startsWith(termoBusca));
    }
}
