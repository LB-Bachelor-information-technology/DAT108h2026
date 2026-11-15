package no.hvl.dat108.oblig5.service;

import no.hvl.dat108.oblig5.model.Deltager;
import no.hvl.dat108.oblig5.repository.DeltagerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DeltagerDbService {

    private final DeltagerRepository deltagerRepository;

    public DeltagerDbService(DeltagerRepository deltagerRepository) {
        this.deltagerRepository = deltagerRepository;
    }

    public List<Deltager> finnAlleSortertPaaFulltNavn() {
        return deltagerRepository.findAll().stream()
                .sorted(Comparator.comparing(Deltager::getFornavn)
                        .thenComparing(Deltager::getEtternavn))
                .toList();
    }

    public Optional<Deltager> finnMedMobil(String mobil) {
        return deltagerRepository.findById(mobil);
    }

    public boolean finnesMedMobil(String mobil) {
        return deltagerRepository.existsById(mobil);
    }

    public Deltager lagre(Deltager deltager) {
        return deltagerRepository.save(deltager);
    }
}