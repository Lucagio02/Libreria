package utility;

import model.Genere;
import model.Libro;
import model.StatoLettura;

import java.util.ArrayList;
import java.util.List;

public class CaricaLibri{

    public static List<Libro> creaLibriEsempio() {
        List<Libro> esempio = new ArrayList<>();

        esempio.add(new Libro.Builder("Il Signore degli Anelli", "9788845292613", "J.R.R. Tolkien")
                .genere(Genere.FANTASY)
                .valutazione(5)
                .statoLettura(StatoLettura.LETTO)
                .build());

        esempio.add(new Libro.Builder("Le notti bianche", "9783863524456", "Fëdor Dostoevskij")
                .genere(Genere.CLASSICS)
                .valutazione(4)
                .statoLettura(StatoLettura.INCORSO)
                .build());

        esempio.add(new Libro.Builder("Il Silmarillion", "9780618391110", "J.R.R. Tolkien")
                .genere(Genere.FANTASY)
                .valutazione(5)
                .statoLettura(StatoLettura.DALEGGERE)
                .build());

        return esempio;
    }
}

