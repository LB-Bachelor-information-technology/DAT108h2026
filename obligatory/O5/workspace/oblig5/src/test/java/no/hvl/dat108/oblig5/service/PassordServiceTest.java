package no.hvl.dat108.oblig5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassordServiceTest {

    private PassordService passordService;

    @BeforeEach
    void setUp() {
        passordService = new PassordService();
    }

    @Test
    void testErPassordKorrektMedGyldigPassord() {
        String passord = "Hemmelig123";
        String salt = passordService.lagTilfeldigSalt();
        String hash = passordService.lagHashetPassord(passord, salt);

        boolean erKorrekt = passordService.erPassordKorrekt(passord, salt, hash);
        assertTrue(erKorrekt, "Skal returnere true for korrekt passord");
    }

    @Test
    void testErPassordKorrektMedFeilPassord() {
        String passord = "Hemmelig123";
        String feilPassord = "FeilPassord";
        String salt = passordService.lagTilfeldigSalt();
        String hash = passordService.lagHashetPassord(passord, salt);

        boolean erKorrekt = passordService.erPassordKorrekt(feilPassord, salt, hash);
        assertFalse(erKorrekt, "Skal returnere false for feil passord");
    }
}