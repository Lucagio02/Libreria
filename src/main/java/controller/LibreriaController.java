package model;


import model.LibreriaFiltro;
import model.Libro;
import model.Libro.Builder;
import model.strategy.*;

import java.util.List;
import java.util.Scanner;

public class LibreriaController {
    private final Libreria libreria;
    private final Scanner scanner;
    private final String FILE_PATH = "libri.json";

    public LibreriaController(Libreria libreria, Scanner scanner) {
        this.libreria = libreria;
        this.scanner = scanner;
    }

    public void avvia() {
        boolean esci = false;
        while (!esci) {
            System.out.println("\n📚 MENU LIBRERIA 📚");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Rimuovi libro");
            System.out.println("3. Mostra tutti i libri");
            System.out.println("4. Cambia ordinamento");
            System.out.println("5. Filtra libri");
            System.out.println("6. Salva su file");
            System.out.println("7. Carica da file");
            System.out.println("8. Esci");
            System.out.print("Scelta: ");
            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> aggiungiLibro();
                case "2" -> rimuoviLibro();
                case "3" -> mostraLibri();
                case "4" -> cambiaOrdinamento();
                case "5" -> filtraLibri();
                case "6" -> libreria.salvaSuFile(FILE_PATH);
                case "7" -> libreria.caricaDaFile(FILE_PATH);
                case "8" -> esci = true;
                default -> System.out.println("❌ Scelta non valida.");
            }
        }
    }

    private void aggiungiLibro() {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Autore: ");
        String autore = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        Builder builder = new Libro.Builder(titolo, autore, isbn);

        System.out.print("Genere (opzionale): ");
        String genere = scanner.nextLine();
        if (!genere.isBlank()) builder.genere(genere);

        System.out.print("Stato lettura (opzionale): ");
        String stato = scanner.nextLine();
        if (!stato.isBlank()) builder.statoLettura(stato);

        System.out.print("Valutazione da 1 a 5 (opzionale): ");
        String rating = scanner.nextLine();
        if (!rating.isBlank()) builder.valutazione(Integer.parseInt(rating));

        System.out.print("Anno di pubblicazione (opzionale): ");
        String anno = scanner.nextLine();
        if (!anno.isBlank()) builder.annoPubblicazione(Integer.parseInt(anno));

        libreria.aggiungiLibro(builder.build());
        System.out.println("✅ Libro aggiunto.");
    }

    private void rimuoviLibro() {
        System.out.print("ISBN del libro da rimuovere: ");
        String isbn = scanner.nextLine();
        boolean rimosso = libreria.rimuoviLibro(isbn);
        System.out.println(rimosso ? "✅ Rimosso" : "❌ Libro non trovato");
    }

    private void mostraLibri() {
        List<Libro> ordinati = libreria.ordinaLibri();
        if (ordinati.isEmpty()) {
            System.out.println("📭 Nessun libro presente.");
        } else {
            ordinati.forEach(System.out::println);
        }
    }

    private void cambiaOrdinamento() {
        System.out.println("🔀 Scegli ordinamento:");
        System.out.println("1. Titolo");
        System.out.println("2. Autore");
        System.out.println("3. Anno");
        System.out.print("Scelta: ");
        switch (scanner.nextLine()) {
            case "1" -> libreria.setStrategiaOrdinamento(new OrdinamentoPerTitolo());
            case "2" -> libreria.setStrategiaOrdinamento(new OrdinamentoPerAutore());
            case "3" -> libreria.setStrategiaOrdinamento(new OrdinamentoPerAnno());
            default -> System.out.println("❌ Scelta non valida.");
        }
    }

    private void filtraLibri() {
        System.out.println("🔎 Filtra per:");
        System.out.println("1. Genere");
        System.out.println("2. Stato lettura");
        System.out.println("3. Autore");
        System.out.print("Scelta: ");
        String scelta = scanner.nextLine();

        System.out.print("Inserisci valore da cercare: ");
        String valore = scanner.nextLine().toLowerCase();

        var filtrati = libreria.ordinaLibri().stream()
                .filter(libro -> switch (scelta) {
                    case "1" -> libro.getGenere() != null && libro.getGenere().toLowerCase().contains(valore);
                    case "2" -> libro.getStatoLettura() != null && libro.getStatoLettura().toLowerCase().contains(valore);
                    case "3" -> libro.getAutore() != null && libro.getAutore().toLowerCase().contains(valore);
                    default -> false;
                }).toList();

        if (filtrati.isEmpty()) {
            System.out.println("📭 Nessun libro trovato.");
        } else {
            filtrati.forEach(System.out::println);
        }
    }
}
