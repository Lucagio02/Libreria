

import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Libreria;
import model.strategy.OrdinamentoPerTitolo;
import utility.isbnvalidator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Testing1 {

    private Libreria libreria;

    @BeforeEach
    void setUp() {
        libreria = new Libreria();
    }

    @Test
    void testAggiuntaLibro() {
        Libro libro = new Libro.Builder("Titolo", "9783161484100", "Autore").build();
        libreria.aggiungiLibro(libro);

        assertTrue(libreria.ordinaLibri().contains(libro), "Il libro dovrebbe essere stato aggiunto");
    }

    @Test
    void testRimozioneLibro() {
        Libro libro = new Libro.Builder("Titolo", "9780131103627", "Autore").build();
        libreria.aggiungiLibro(libro);
        boolean rimosso = libreria.rimuoviLibro("9780131103627");

        assertTrue(rimosso, "Il libro dovrebbe essere stato rimosso");
        assertFalse(libreria.ordinaLibri().contains(libro), "Il libro non dovrebbe più essere nella libreria");
    }
    @Test
    void testOrdinamentoPerTitolo() {
            Libreria libreria = new Libreria();
        String isbn1 = "9780140449136";  // l'ISBN valido
        System.out.println("Controllo validità isbn1: "+ isbnvalidator.isvalid(isbn1) );
            Libro libro1 = new Libro.Builder("Adoreta", isbn1, "George Orwell").build();
            Libro libro2 = new Libro.Builder("Il Piccolo Principe", "9780306406157", "Antoine de Saint-Exupéry").build();
            Libro libro3 = new Libro.Builder("Harry Potter e la pietra filosofale", "9783161484100", "J.K. Rowling").build();
            libreria.aggiungiLibro(libro1);
            libreria.aggiungiLibro(libro2);
            libreria.aggiungiLibro(libro3);

            libreria.setStrategiaOrdinamento(new OrdinamentoPerTitolo());
            List<Libro> ordinati = libreria.ordinaLibri();

            assertEquals("Adoreta", ordinati.get(0).getTitolo());
            assertEquals("Harry Potter e la pietra filosofale", ordinati.get(1).getTitolo());
            assertEquals("Il Piccolo Principe", ordinati.get(2).getTitolo());
        }

        private final String testFile = "test_libreria.json";

    @Test
    void testSalvataggioCaricamento() {
        Libreria libreria = new Libreria();
        Libro libro1 = new Libro.Builder("Il titolo", "9781861972712", "0001").build();
        libreria.aggiungiLibro(libro1);
        libreria.salvaSuFile(testFile);

        Libreria nuovaLibreria = new Libreria();
        nuovaLibreria.caricaDaFile(testFile,true);
        List<Libro> caricati = nuovaLibreria.ordinaLibri();

        assertEquals(1, caricati.size());
        assertEquals("Il titolo", caricati.get(0).getTitolo());
    }


}
