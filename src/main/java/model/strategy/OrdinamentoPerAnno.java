package model.strategy;

import model.Libro;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoPerAnno implements OrdinamentoStrategy {
    @Override
    public List<Libro> ordina(List<Libro> libri) {
        return libri.stream()
                .sorted(Comparator.comparingInt(Libro::getAnnoPubblicazione))
                .toList();
    }
}