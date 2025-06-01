package view.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Genere;
import model.Libro;
import model.StatoLettura;

public class LibroDettagliController {
    @FXML private Label titoloLabel;
    @FXML private Label autoreLabel;
    @FXML private Label isbnLabel;

    @FXML private ComboBox<Genere> genereComboBox;
    @FXML private ComboBox<StatoLettura> statoComboBox;
    @FXML private Spinner<Integer> valutazioneSpinner;
    @FXML private TextField annoField;
    @FXML private Button salvaButton;
    private Libro libro;

    @FXML
    private void initialize() {
        genereComboBox.getItems().setAll(Genere.values());
        statoComboBox.getItems().setAll(StatoLettura.values());
        valutazioneSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3));
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
        titoloLabel.setText(libro.getTitolo());
        autoreLabel.setText(libro.getAutore());
        isbnLabel.setText(libro.getIsbn());

        genereComboBox.setValue(libro.getGenere());
        statoComboBox.setValue(libro.getStatoLettura());
        valutazioneSpinner.getValueFactory().setValue(libro.getValutazione());
        annoField.setText(libro.getAnnoPubblicazione() > 0 ? String.valueOf(libro.getAnnoPubblicazione()) : "");

    }
    @FXML
    private void handleSalva() {
        libro.setGenere(genereComboBox.getValue());
        libro.setStatoLettura(statoComboBox.getValue());
        libro.setValutazione(valutazioneSpinner.getValue());

        try {
            int anno = Integer.parseInt(annoField.getText());
            libro.setAnnoPubblicazione(anno);
        } catch (NumberFormatException e) {
            utility.Avviso.mostraErrore("Errore", "Anno non valido");
            return;
        }

        Stage stage = (Stage) salvaButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChiudi() {
        Stage stage = (Stage) titoloLabel.getScene().getWindow();
        stage.close();
    }
}


