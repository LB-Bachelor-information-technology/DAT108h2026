package no.hvl.dat108.oblig5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DeltagerPostRequestDTO {

    @NotBlank(message = "Mobilnummer kan ikke være tomt")
    @Pattern(regexp = "^\\d{8}$", message = "Mobilnummer må bestå av nøyaktig 8 sifre")
    private String mobil;

    @NotBlank(message = "Passord kan ikke være tomt")
    @Size(min = 4, message = "Passord må være minst 4 tegn")
    private String passord;

    @Size(max = 40, message = "Fornavn kan maks være 40 tegn")
    private String fornavn;

    @Size(max = 40, message = "Etternavn kan maks være 40 tegn")
    private String etternavn;

    @Pattern(regexp = "^(mann|kvinne|annen)?$", message = "Ugyldig kjønn")
    private String kjonn;

    public String getMobil() { return mobil; }
    public void setMobil(String mobil) { this.mobil = mobil; }

    public String getPassord() { return passord; }
    public void setPassord(String passord) { this.passord = passord; }

    public String getFornavn() { return fornavn; }
    public void setFornavn(String fornavn) { this.fornavn = fornavn; }

    public String getEtternavn() { return etternavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }

    public String getKjonn() { return kjonn; }
    public void setKjonn(String kjonn) { this.kjonn = kjonn; }
}