package no.hvl.dat108.oblig5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deltager", schema = "dat108_oblig5_h26")
public class Deltager {


    @Id
    @Column(name = "mobil", columnDefinition = "CHARACTER(8)")
    private String mobil;

    @Column(name = "passordhash", nullable = false, columnDefinition = "CHARACTER(64)")
    private String passordhash;

    @Column(name = "fornavn", length = 40)
    private String fornavn;

    @Column(name = "etternavn", length = 40)
    private String etternavn;

    @Column(name = "kjonn", length = 6)
    private String kjonn;

    public Deltager() {
    }

    public Deltager(String mobil, String passordhash, String fornavn, String etternavn, String kjonn) {
        this.mobil = mobil;
        this.passordhash = passordhash;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjonn = kjonn;
    }

    public String getMobil() { return mobil; }
    public void setMobil(String mobil) { this.mobil = mobil; }

    public String getPassordhash() { return passordhash; }
    public void setPassordhash(String passordhash) { this.passordhash = passordhash; }

    public String getFornavn() { return fornavn; }
    public void setFornavn(String fornavn) { this.fornavn = fornavn; }

    public String getEtternavn() { return etternavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }

    public String getKjonn() { return kjonn; }
    public void setKjonn(String kjonn) { this.kjonn = kjonn; }
}