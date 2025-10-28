package br.ufac.sgcmapi.atendimento;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.convenio.Convenio;
import br.ufac.sgcmapi.paciente.Paciente;
import br.ufac.sgcmapi.profissional.Profissional;

@ExtendWith(MockitoExtension.class)
class AtendimentoTest {

    @Mock
    private Profissional mockProfissional;

    @Mock
    private Convenio mockConvenio;

    @Mock
    private Paciente mockPaciente;

    private Atendimento atendimento;

    @BeforeEach
    void setUp() {
            atendimento = new Atendimento();
    }

    @Test
    void testAtendimentoId() {
        var id = 1L;
        atendimento.setId(id);
        assertEquals(1L, atendimento.getId());
    }

    @Test
    void testAtendimentoNovoAtendimento() {
        
        var id = 3L;
        var data = LocalDate.of(2025, 10, 20);
        var hora = LocalTime.parse("15:23:00");
        var eStatus = EStatus.CONFIRMADO;
        
        atendimento.setId(id);
        atendimento.setData(data);
        atendimento.setHora(hora);
        atendimento.setProfissional(mockProfissional);
        atendimento.setConvenio(mockConvenio);
        atendimento.setPaciente(mockPaciente);
        atendimento.setStatus(eStatus);

        assertEquals(3L, atendimento.getId());
        assertEquals(LocalDate.of(2025, 10, 20), atendimento.getData());
        assertEquals(LocalTime.of(15, 23), atendimento.getHora());
        assertEquals(EStatus.CONFIRMADO, atendimento.getStatus());
        
        assertEquals(mockProfissional, atendimento.getProfissional());
        assertEquals(mockConvenio, atendimento.getConvenio());
        assertEquals(mockPaciente, atendimento.getPaciente());
    };
}
