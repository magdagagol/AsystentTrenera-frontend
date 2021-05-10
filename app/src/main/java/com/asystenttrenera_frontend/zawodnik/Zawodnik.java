package com.asystenttrenera_frontend.zawodnik;

public class Zawodnik {
    private Long id;
    private String imie;
    private String nazwisko;
    private String rokUrodzenia;
    private String email;
    private String numerTelefonu;

    public Zawodnik(Long id, String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void setRokUrodzenia(String rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }
}
