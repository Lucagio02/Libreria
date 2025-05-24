package model;

public enum StatoLettura {
    LETTO("Letto"),
    DALEGGERE("Da Leggere"),
    INCORSO("In Corso"),
    ABBANDONATO("Abbandonato");

    private final String descrizione;
    StatoLettura(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getStatoLettura() { return descrizione; }
    @Override
    public String toString() {
        return descrizione;
    }
}
