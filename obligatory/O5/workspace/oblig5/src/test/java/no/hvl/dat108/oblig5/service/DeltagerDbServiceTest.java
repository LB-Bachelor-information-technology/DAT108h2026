package no.hvl.dat108.oblig5.service;

import no.hvl.dat108.oblig5.model.Deltager;
import no.hvl.dat108.oblig5.repository.DeltagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeltagerDbServiceTest {

    @Mock
    private DeltagerRepository deltagerRepository;

    @InjectMocks
    private DeltagerDbService deltagerDbService;

    @Test
    void testFinnAlleSortertPaaFulltNavn() {
        // Oppretter testdata med ulike navn for å sjekke sortering på etternavn, deretter fornavn
        Deltager d1 = new Deltager("11111111", "hash1", "Kari", "Berg", "kvinne");
        Deltager d2 = new Deltager("22222222", "hash2", "Anne", "Andersen", "kvinne");
        Deltager d3 = new Deltager("33333333", "hash3", "Per", "Berg", "mann");

        // Vi mocker repositoryet til å returnere disse i usortert rekkefølge
        when(deltagerRepository.findAll()).thenReturn(Arrays.asList(d1, d2, d3));

        List<Deltager> sorterte = deltagerDbService.finnAlleSortertPaaFulltNavn();

        // Forventet rekkefølge: Andersen (Anne), Berg (Kari), Berg (Per)
        assertEquals("22222222", sorterte.get(0).getMobil());
        assertEquals("11111111", sorterte.get(1).getMobil());
        assertEquals("33333333", sorterte.get(2).getMobil());
    }
}