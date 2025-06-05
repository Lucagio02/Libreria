package view.model;
import model.Libro;


import model.Genere;

import model.StatoLettura;

public class Libreriaviewmodel {

    private String titolo = "";
    private String autore = "";
    private String isbn = "";
    private Genere genere = null;
    private StatoLettura stato = null;

    public void setTitolo(String titolo) { this.titolo = titolo.toLowerCase(); }
    public void setAutore(String autore) { this.autore = autore.toLowerCase(); }
    public void setIsbn(String isbn) { this.isbn = isbn.toLowerCase(); }
    public void setGenere(Genere genere) { this.genere = genere; }
    public void setStato(StatoLettura stato) { this.stato = stato; }

    public boolean test(Libro libro) {
        if (!libro.getTitolo().toLowerCase().contains(titolo)) return false;
        if (!libro.getAutore().toLowerCase().contains(autore)) return false;
        if (!libro.getIsbn().toLowerCase().contains(isbn)) return false;
        if (genere != null && libro.getGenere() != genere) return false;
        if (stato != null && libro.getStatoLettura() != stato) return false;
        return true;
    }
}


