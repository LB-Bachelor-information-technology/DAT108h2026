package no.hvl.dat108.oblig5.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiNokkelService {

    @Value("${app.api-key}")
    private String gyldigApiNokkel;

    public boolean sjekkApiNokkel(String mottattNokkel) {
        return gyldigApiNokkel != null && gyldigApiNokkel.equals(mottattNokkel);
    }
}