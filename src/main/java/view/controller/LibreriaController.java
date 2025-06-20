package view.controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Genere;
import model.StatoLettura;
import service.Libreria;
import utility.validatore;
import utility.Avviso;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import model.Libro;
import view.controller.LibroDettagliController;

import java.io.IOException;
import java.util.Optional;

public class LibreriaController {

    @FXML private TableView<Libro> tableLibri;
    @FXML private TableColumn<Libro, String> colTitolo;
    @FXML private TableColumn<Libro, String> colAutore;
    @FXML private TableColumn<Libro, String> colIsbn;
    @FXML private TableColumn<Libro, String> colGenere;
    @FXML private TableColumn<Libro, Integer> colValutazione;
    @FXML private TableColumn<Libro, String> colStato;
    @FXML private TableColumn<Libro, Integer> colAnno;

    @FXML
    private void initialize() {
        inizializzaColonneTabella();
        inizializzaFiltri();
        inizializzaDoppioClick();
    }
    private void inizializzaColonneTabella() {
        colTitolo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitolo()));
        colAutore.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutore()));
        colIsbn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));
        colGenere.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getGenere() != null ? data.getValue().getGenere().toString() : ""
        ));
        colValutazione.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValutazione()).asObject());
        colStato.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getStatoLettura() != null ? data.getValue().getStatoLettura().toString() : ""
        ));
        colAnno.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAnnoPubblicazione()).asObject());

        tableLibri.setItems(FXCollections.observableArrayList(Libreria.getInstance().getLibri()));
    }
    private void inizializzaFiltri() {
        filtroGenereBox.getItems().addAll(Genere.values());
        filtroStatoBox.getItems().addAll(StatoLettura.values());

        filtroTitoloField.textProperty().addListener((obs, oldV, newV) -> applicaFiltri());
        filtroAutoreField.textProperty().addListener((obs, oldV, newV) -> applicaFiltri());
        filtroIsbnField.textProperty().addListener((obs, oldV, newV) -> applicaFiltri());
        filtroGenereBox.valueProperty().addListener((obs, oldV, newV) -> applicaFiltri());
        filtroStatoBox.valueProperty().addListener((obs, oldV, newV) -> applicaFiltri());
    }
    private void inizializzaDoppioClick() {
        tableLibri.setRowFactory(tv -> {
            TableRow<Libro> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Libro libro = row.getItem();
                    apriDettagliLibro(libro);
                }
            });
            return row;
        });
    }
    @FXML
    private TextField inputTitolo;

    @FXML
    private TextField inputAutore;

    @FXML
    private TextField inputIsbn;
    @FXML private TextField filtroTitoloField;
    @FXML private TextField filtroAutoreField;
    @FXML private TextField filtroIsbnField;
    @FXML private ComboBox<Genere> filtroGenereBox;
    @FXML private ComboBox<StatoLettura> filtroStatoBox;

    @FXML
    private void handleAggiungiLibro() {
        String titolo = inputTitolo.getText();
        String isbn = inputIsbn.getText();
        String autore = inputAutore.getText();

        if(validatore.campiVuoti(titolo, isbn, autore)){
            Avviso.mostraAvviso("Campi Incompleti","Compila tutti i campi vuoti");
            return;
        }

        try{
            Libro nuovoLibro= new Libro.Builder(titolo, isbn, autore).build();
            Libreria.getInstance().getLibri().add(nuovoLibro);

            tableLibri.getItems().add(nuovoLibro);

            inputTitolo.clear();
            inputAutore.clear();
            inputIsbn.clear();

        }
        catch(IllegalArgumentException e){
             Avviso.mostraErrore("ISbn non valido",e.getMessage());
        }
    }
    @FXML
    private void apriDettagliLibro(Libro libro) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LibroDettagli.fxml"));
            Parent root = loader.load();

            LibroDettagliController controller = loader.getController();
            controller.setLibro(libro);

            Stage stage = new Stage();
            stage.setTitle("Dettagli Libro");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            tableLibri.refresh(); // aggiorna i dati se sono stati modificati

        } catch (IOException e) {
            e.printStackTrace();
            Avviso.mostraErrore("Errore", "Impossibile aprire i dettagli del libro");
        }
    }

    @FXML
    private void handleSalvataggio() {
        try {
            Libreria.getInstance().salvaSuFile("libri.json");
            Avviso.mostraAvviso("Salvataggio riuscito", "I dati del libro sono stati correttamente salvati");
        }catch (Exception e){
            Avviso.mostraErrore("Salvataggio non valido","Impossibile slavare i dati"+e.getMessage());
        }

    }
    @FXML
    private void handleRimuoviLibro() {
        Libro libroSelezionato = tableLibri.getSelectionModel().getSelectedItem();
        if (libroSelezionato != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma rimozione");
            alert.setHeaderText("Vuoi davvero rimuovere il libro selezionato?");
            alert.setContentText(libroSelezionato.getTitolo());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Libreria.getInstance().rimuoviLibro(libroSelezionato);
                tableLibri.getItems().remove(libroSelezionato);
                Libreria.getInstance().salvaSuFile("libri.json");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText("Nessun libro selezionato");
            alert.setContentText("Seleziona un libro dalla tabella per rimuoverlo.");
            alert.showAndWait();
        }
    }
    @FXML
    private void applicaFiltri() {
        String titolo = filtroTitoloField.getText().toLowerCase();
        String autore = filtroAutoreField.getText().toLowerCase();
        String isbn = filtroIsbnField.getText().toLowerCase();
        Genere genere = filtroGenereBox.getValue();
        StatoLettura stato = filtroStatoBox.getValue();

        ObservableList<Libro> filtrati = FXCollections.observableArrayList();
        for (Libro libro : Libreria.getInstance().getLibri()) {
            boolean matchTitolo = libro.getTitolo().toLowerCase().contains(titolo);
            boolean matchAutore = libro.getAutore().toLowerCase().contains(autore);
            boolean matchIsbn = libro.getIsbn().toLowerCase().contains(isbn);
            boolean matchGenere = genere == null || libro.getGenere() == genere;
            boolean matchStato = stato == null || libro.getStatoLettura() == stato;

            if (matchTitolo && matchAutore && matchIsbn && matchGenere && matchStato) {
                filtrati.add(libro);
            }
        }
        tableLibri.setItems(filtrati);
    }
    @FXML
    private void handleAzzeraFiltri() {
        filtroTitoloField.clear();
        filtroAutoreField.clear();
        filtroIsbnField.clear();
        filtroGenereBox.getSelectionModel().clearSelection();
        filtroStatoBox.getSelectionModel().clearSelection();

        tableLibri.setItems(FXCollections.observableArrayList(Libreria.getInstance().getLibri()));
    }




}
