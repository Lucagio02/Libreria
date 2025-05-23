package model;

import model.strategy.OrdinamentoPerTitolo;
import model.strategy.OrdinamentoStrategy;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class Libreria {
    private final List<Libro> libri;
    private OrdinamentoStrategy strategyOrdinamento;

    public Libreria() {
        this.libri = new ArrayList<>();
        this.strategyOrdinamento= new OrdinamentoPerTitolo();
    }

    public void aggiungiLibro(Libro libro) {
        libri.add(libro);
    }

    public void setStrategiaOrdinamento(OrdinamentoStrategy strategia) {
        this.strategyOrdinamento = strategia;
    }

    public List<Libro> ordinaLibri() {  //
        if (strategyOrdinamento == null) {
            throw new IllegalStateException("Strategy di ordinamento non impostata!");
        }
        return strategyOrdinamento.ordina(libri);
    }
    public boolean rimuoviLibro(String isbn) {
        return libri.removeIf(l -> l.getIsbn().equalsIgnoreCase(isbn));
    }

    public void salvaSuFile(String percorso) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(percorso)) {
            gson.toJson(libri, writer);
            System.out.println("Libreria salvata con successo su: " + percorso);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public void caricaDaFile(String percorso) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(percorso)) {
            Type listType = new TypeToken<List<Libro>>() {}.getType();
            List<Libro> libriCaricati = gson.fromJson(reader, listType);
            libri.clear();
            libri.addAll(libriCaricati);
            System.out.println("Libreria caricata da: " + percorso);
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
        }
    }
}