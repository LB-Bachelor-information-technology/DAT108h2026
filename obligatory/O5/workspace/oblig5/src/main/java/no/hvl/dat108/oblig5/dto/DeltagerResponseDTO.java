package no.hvl.dat108.oblig5.dto;

import no.hvl.dat108.oblig5.model.Deltager;

public class DeltagerResponseDTO {

    private String mobil;
    private String fornavn;
    private String etternavn;
    private String kjonn;

    public DeltagerResponseDTO(Deltager deltager) {
        this.mobil = deltager.getMobil();
        this.fornavn = deltager.getFornavn();
        this.etternavn = deltager.getEtternavn();
        this.kjonn = deltager.getKjonn();
    }

    public String getMobil() { return mobil; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public String getKjonn() { return kjonn; }
}