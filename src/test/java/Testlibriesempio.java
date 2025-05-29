import model.Libro;
import org.junit.jupiter.api.*;
import service.Libreria;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;

class Usalibriesempio {

    private Libreria libreria;
    private static final String FILE_VUOTO = "test/resources/libri_vuoto.json";

    @BeforeEach
    void setUp() throws IOException {
        libreria = new Libreria();
        // Crea un file vuoto per il test
        Files.createDirectories(Paths.get("test/resources"));
        Files.write(Paths.get(FILE_VUOTO), new byte[0]);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Elimina il file di test dopo l'esecuzione
        Files.deleteIfExists(Paths.get(FILE_VUOTO));
    }

    @Test
    void testCaricaLibriDiEsempioSeFileVuoto() {
        libreria.caricaDaFile(FILE_VUOTO, true);
        List<Libro> libri = libreria.getLibri();
        assertFalse(libri.isEmpty(), "La libreria dovrebbe contenere libri di esempio.");
    }
    @Test
    void testLibreriaVuotaSeFileVuotoENonCaricareEsempi() {
        libreria.caricaDaFile(FILE_VUOTO, false);
        List<Libro> libri = libreria.getLibri();
        assertTrue(libri.isEmpty(), "La libreria dovrebbe essere vuota.");
    }

}

