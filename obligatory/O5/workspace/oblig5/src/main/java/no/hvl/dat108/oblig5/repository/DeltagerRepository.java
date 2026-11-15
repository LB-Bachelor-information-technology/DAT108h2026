package no.hvl.dat108.oblig5.repository;

import no.hvl.dat108.oblig5.model.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeltagerRepository extends JpaRepository<Deltager, String> {
}