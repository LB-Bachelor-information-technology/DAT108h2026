package no.hvl.dat108.oblig5.controller;

import jakarta.validation.Valid;
import no.hvl.dat108.oblig5.dto.DeltagerPostRequestDTO;
import no.hvl.dat108.oblig5.dto.DeltagerResponseDTO;
import no.hvl.dat108.oblig5.model.Deltager;
import no.hvl.dat108.oblig5.service.ApiNokkelService;
import no.hvl.dat108.oblig5.service.DeltagerDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deltagere")
public class ApiController {

    private final DeltagerDbService deltagerDbService;
    private final ApiNokkelService apiNokkelService;

    public ApiController(DeltagerDbService deltagerDbService, ApiNokkelService apiNokkelService) {
        this.deltagerDbService = deltagerDbService;
        this.apiNokkelService = apiNokkelService;
    }

    @GetMapping
    public ResponseEntity<List<DeltagerResponseDTO>> hentAlleDeltagere(
            @RequestHeader(value = "X-API-Key", required = false) String apiKey) {

        if (!apiNokkelService.sjekkApiNokkel(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<DeltagerResponseDTO> deltagere = deltagerDbService.finnAlleSortertPaaFulltNavn().stream()
                .map(DeltagerResponseDTO::new)
                .toList();

        return ResponseEntity.ok(deltagere);
    }

    @GetMapping("/{mobil}")
    public ResponseEntity<DeltagerResponseDTO> hentDeltager(
            @PathVariable String mobil,
            @RequestHeader(value = "X-API-Key", required = false) String apiKey) {

        if (!apiNokkelService.sjekkApiNokkel(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Deltager> deltager = deltagerDbService.finnMedMobil(mobil);
        if (deltager.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(new DeltagerResponseDTO(deltager.get()));
    }

    @PostMapping
    public ResponseEntity<DeltagerResponseDTO> opprettDeltager(
            @Valid @RequestBody DeltagerPostRequestDTO dto,
            @RequestHeader(value = "X-API-Key", required = false) String apiKey) {

        if (!apiNokkelService.sjekkApiNokkel(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (deltagerDbService.finnesMedMobil(dto.getMobil())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String hash = hashPassord(dto.getPassord());
        Deltager nyDeltager = new Deltager(dto.getMobil(), hash, dto.getFornavn(), dto.getEtternavn(), dto.getKjonn());
        deltagerDbService.lagre(nyDeltager);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DeltagerResponseDTO(nyDeltager));
    }

    private String hashPassord(String passord) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passord.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Klarte ikke å hashe passord", e);
        }
    }
}