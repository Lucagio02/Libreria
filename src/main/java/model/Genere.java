package model;

public enum Genere {

    FANTASY("Fantasy"),
    SCIENCE_FICTION("Fantascienza"),
    THRILLER("Thriller"),
    MYSTERY("Giallo"),
    ROMANCE("Romanzo Rosa"),
    HISTORICAL_FICTION("Romanzo Storico"),
    HORROR("Horror"),
    BIOGRAPHY("Biografia"),
    AUTOBIOGRAPHY("Autobiografia"),
    POETRY("Poesia"),
    ESSAY("Saggio"),
    CHILDREN("Libri per Ragazzi"),
    YOUNG_ADULT("Young Adult"),
    CONTEMPORARY("Contemporaneo"),
    CLASSICS("Classici"),
    OTHER("Altro"); // Un'opzione generica se necessario

    private final String descrizione;
    private Genere(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() { return descrizione; }

    @Override
    public String toString() { return descrizione; }




}
