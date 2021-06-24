package com.asystenttrenera_frontend.zawodnik;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Zawodnik {
    @PrimaryKey(autoGenerate = true)
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

    public Zawodnik(String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Long getId() {
        return id;
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
