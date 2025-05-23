package model;
import model.Libreria;
import model.Libro;

import java.util.List;
import java.util.Scanner;


public class LibreriaFiltro {
    private final Libreria libreria;
    private final Scanner scanner;

    public LibreriaFiltro(Libreria libreria, Scanner scanner) {
        this.libreria = libreria;
        this.scanner = scanner;
    }

    public void filtraLibri() {
        System.out.println("🔎 Filtra per:");
        System.out.println("1. Genere");
        System.out.println("2. Stato lettura");
        System.out.println("3. Autore");
        System.out.print("Scelta: ");
        String scelta = scanner.nextLine();

        System.out.print("Inserisci il valore da cercare: ");
        String valore = scanner.nextLine().toLowerCase();

        List<Libro> filtrati = libreria.ordinaLibri().stream()
                .filter(libro -> switch (scelta) {
                    case "1" -> libro.getGenere() != null && libro.getGenere().toLowerCase().contains(valore);
                    case "2" -> libro.getStatoLettura() != null && libro.getStatoLettura().toLowerCase().contains(valore);
                    case "3" -> libro.getAutore() != null && libro.getAutore().toLowerCase().contains(valore);
                    default -> false;
                })
                .toList();

        if (filtrati.isEmpty()) {
            System.out.println("📭 Nessun libro trovato con questi criteri.");
        } else {
            System.out.println("📚 Libri trovati:");
            filtrati.forEach(System.out::println);
        }
    }
}

