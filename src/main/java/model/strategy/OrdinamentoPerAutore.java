package model.strategy;

import model.Libro;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerAutore implements OrdinamentoStrategy {
    @Override
    public List<Libro> ordina(List<Libro> libri) {
        return libri.stream()
                .sorted(Comparator.comparing(Libro::getAutore, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}