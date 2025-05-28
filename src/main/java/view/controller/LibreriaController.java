package view.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Libro;

public class LibreriaController {

    @FXML private TableView<Libro> tableLibri;
    @FXML private TableColumn<Libro, String> colTitolo;
    @FXML private TableColumn<Libro, String> colAutore;
    @FXML private TableColumn<Libro, String> colIsbn;
    @FXML
    private void initialize() {
        // Collegamento delle colonne agli attributi
        colTitolo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitolo()));
        colAutore.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutore()));
        colIsbn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIsbn()));

        // Popola con dati fittizi o dal modello
        // tableLibri.setItems(...);
    }
    @FXML
    private TextField inputTitolo;

    @FXML
    private TextField inputAutore;

    @FXML
    private TextField inputIsbn;
    @FXML
    private void handleAggiungiLibro() {
        String titolo = inputTitolo.getText();
        String isbn = inputIsbn.getText();
        String autore = inputAutore.getText();
        try{
            Libro nuovoLibro= new Libro.Builder(titolo, isbn, autore).build();
            tableLibri.getItems().add(nuovoLibro);

            inputTitolo.clear();
            inputAutore.clear();
            inputIsbn.clear();

        }
        catch(IllegalArgumentException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("ISBN non valido");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        }
    }

}
