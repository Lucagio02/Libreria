package service;
import utility.CaricaLibri;

import model.Libro;
import model.strategy.OrdinamentoPerTitolo;
import model.strategy.OrdinamentoStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Libreria {
    private final List<Libro> libri;
    private OrdinamentoStrategy strategyOrdinamento;

    public Libreria() {
        this.libri = new ArrayList<>();
        this.strategyOrdinamento= new OrdinamentoPerTitolo();
    }
    public List<Libro> getLibri() {
        return libri;
    }

    private static Libreria instance;
    public static synchronized Libreria getInstance() {
        if (instance == null) {
            instance = new Libreria();
        }
        return instance;
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
    public void rimuoviLibro(Libro libro) {
        libri.remove(libro);
    }
    public void salvaSuFile(String percorso) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(percorso)) {
            gson.toJson(libri, writer);
            File file = new File(percorso);
            System.out.println("Libreria salvata con successo su: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public void caricaDaFile(String percorso, boolean usaLibriEsempioSeVuoto) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(percorso)) {
            Type listType = new TypeToken<List<Libro>>() {}.getType();
            List<Libro> libriCaricati = gson.fromJson(reader, listType);

            if (libriCaricati == null || libriCaricati.isEmpty()) {
                System.out.println("📂 File vuoto o malformato.");
                libri.clear();

                if (usaLibriEsempioSeVuoto) {
                    System.out.println("📚 Carico libri di esempio...");
                    libri.addAll(CaricaLibri.creaLibriEsempio());
                } else {
                    System.out.println("📝 Libreria inizialmente vuota.");
                }
            } else {
                libri.clear();
                libri.addAll(libriCaricati);
                System.out.println("Libreria caricata da: " + percorso);
            }

        } catch (FileNotFoundException e) {
            System.out.println("📂 File non trovato.");
            libri.clear();

            if (usaLibriEsempioSeVuoto) {
                System.out.println("📚 Carico libri di esempio...");
                libri.addAll(CaricaLibri.creaLibriEsempio());
            } else {
                System.out.println("📝 Libreria inizialmente vuota.");
            }

        } catch (IOException e) {
            System.err.println("❌ Errore durante il caricamento: " + e.getMessage());
        }
    }


    }
