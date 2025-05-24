

import model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Libreria;
import model.strategy.OrdinamentoPerTitolo;

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
        Libro libro = new Libro.Builder("Titolo", "123456", "Autore").build();
        libreria.aggiungiLibro(libro);

        assertTrue(libreria.ordinaLibri().contains(libro), "Il libro dovrebbe essere stato aggiunto");
    }

    @Test
    void testRimozioneLibro() {
        Libro libro = new Libro.Builder("Titolo", "123456", "Autore").build();
        libreria.aggiungiLibro(libro);
        boolean rimosso = libreria.rimuoviLibro("123456");

        assertTrue(rimosso, "Il libro dovrebbe essere stato rimosso");
        assertFalse(libreria.ordinaLibri().contains(libro), "Il libro non dovrebbe più essere nella libreria");
    }
    @Test
    void testOrdinamentoPerTitolo() {
        Libreria libreria = new Libreria();
        Libro libro1 = new Libro.Builder("Zorro", "Autore1", "123").build();
        Libro libro2 = new Libro.Builder("Anna", "Autore2", "456").build();
        Libro libro3 = new Libro.Builder("Libro", "Autore3", "789").build();
            libreria.aggiungiLibro(libro1);
            libreria.aggiungiLibro(libro2);
            libreria.aggiungiLibro(libro3);

            libreria.setStrategiaOrdinamento(new OrdinamentoPerTitolo());
            List<Libro> ordinati = libreria.ordinaLibri();

            assertEquals("Anna", ordinati.get(0).getTitolo());
            assertEquals("Libro", ordinati.get(1).getTitolo());
            assertEquals("Zorro", ordinati.get(2).getTitolo());
        }
    private final String testFile = "test_libreria.json";

    @Test
    void testSalvataggioCaricamento() {
        Libreria libreria = new Libreria();
        Libro libro1 = new Libro.Builder("Il titolo", "Autore", "0001").build();
        libreria.aggiungiLibro(libro1);
        libreria.salvaSuFile(testFile);

        Libreria nuovaLibreria = new Libreria();
        nuovaLibreria.caricaDaFile(testFile);
        List<Libro> caricati = nuovaLibreria.ordinaLibri();

        assertEquals(1, caricati.size());
        assertEquals("Il titolo", caricati.get(0).getTitolo());
    }


}
