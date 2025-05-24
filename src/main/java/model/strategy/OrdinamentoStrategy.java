package model.strategy;

import model.Libro;
import java.util.List;

public interface OrdinamentoStrategy {
    List<Libro> ordina(List<Libro> libri);
}