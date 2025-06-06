package model;
import utility.isbnvalidator;
public class Libro {
    private final String titolo;
    private final String isbn;
    private final String autore;
    private Genere genere;
    private final int valutazione;
    private StatoLettura statoLettura;
    private final int annoPubblicazione;

    private Libro(Builder builder) {
        this.titolo = builder.titolo;
        this.isbn = builder.isbn;
        this.autore = builder.autore;
        this.genere = builder.genere;
        this.valutazione = builder.valutazione;
        this.statoLettura = builder.statoLettura;
        this.annoPubblicazione = builder.annoPubblicazione;
    }

    public static class Builder {
        private final String titolo;
        private final String isbn;
        private final String autore;

        private Genere genere;
        private int valutazione = 0;
        private StatoLettura statoLettura;
        private int annoPubblicazione = -1;  // -1 = non specificato

        public Builder(String titolo, String isbn, String autore) {
            this.titolo = titolo;
            if(!isbnvalidator.isbnvalidator(isbn)){
                throw new IllegalArgumentException("ISBN non è valido, si prega di reinserlo");
            }
            this.isbn = isbn;
            this.autore = autore;
        }

        public Builder genere(Genere genere) {
            this.genere = genere;
            return this;
        }

        public Builder valutazione(int valutazione) {
            this.valutazione = valutazione;
            if (valutazione > 0 && valutazione <=5) {//obbligatoriamente da 0 a 5 in cui 0 è quando non è ancora letto
                return this;
            }
            throw new IllegalArgumentException("Valutazione non valida");
        }

        public Builder statoLettura(StatoLettura stato) {
            this.statoLettura = stato;
            if (stato != StatoLettura.LETTO) {//in modo tale che se il libro non sia stato letto non si possà fare una valutazione
                this.valutazione = 0;
            }
            return this;
        }

        public Builder annoPubblicazione(int anno) {
            this.annoPubblicazione = anno;
            return this;
        }

        public Libro build() {
            return new Libro(this);
        }
    }

    // Getter
    public String getTitolo() { return titolo; }
    public String getIsbn() { return isbn; }
    public String getAutore() { return autore; }
    public Genere getGenere() { return genere; }
    public int getValutazione() { return valutazione; }
    public StatoLettura getStatoLettura() { return statoLettura; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }

    @Override
    public String toString() {
        return titolo + " di " + autore +
                " (" + genere.toString() + ", " + (annoPubblicazione > 0 ? annoPubblicazione : "Anno n/d") + ") - Stato: " +
                statoLettura + ", Valutazione: " + valutazione + "★";
    }
}
