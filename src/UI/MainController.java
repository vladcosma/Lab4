package UI;

import Domain.Client;
import Domain.Medicament;
import Service.ClientService;
import Service.MedicamentService;
import Service.TransactionService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    @FXML
    //Mecicament
    public TableView tableViewMedicaments;
    public TableColumn tableColumnId;
    public TableColumn tableColumnName;
    public TableColumn tableColumnManufacturer;
    public TableColumn tableColumnPrice;
    public TableColumn tableColumnNeedRecipe;

    public Button btnMedAdd;
    public Button btnMedDelete;

    //client
    @FXML
    public TableView tableViewClients;
    public TableColumn tableColumnClId;
    public TableColumn tableColumnClFirstName;
    public TableColumn tableColumnClLastName;
    public TableColumn tableColumnClCNP;
    public TableColumn tableColumnClDateOfReg;
    public TableColumn tableColumnClDateOfBirth;

    public Button btnClAdd;
    public Button btnClDelete;


    private MedicamentService medicamentService;
    private ClientService clientService;
    private TransactionService transactionService;

    private ObservableList<Medicament> medicaments = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public void setServices(MedicamentService medicamentService, ClientService clientService, TransactionService transactionService) {
        this.medicamentService = medicamentService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }


    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            medicaments.addAll(medicamentService.getAll());
            tableViewMedicaments.setItems(medicaments);

            clients.addAll(clientService.getAll());
            tableViewClients.setItems(clients);

        });
    }

    public void editMedName(TableColumn.CellEditEvent cellEditEvent) {
        Medicament editedMedicament = (Medicament)cellEditEvent.getRowValue();
        try {
            String newName = (String)cellEditEvent.getNewValue();
            medicamentService.addOrUpdate(editedMedicament.getId(), newName,editedMedicament.getManufacturer(), editedMedicament.getPrice(), editedMedicament.isNeedRecipe());
            editedMedicament.setName(newName);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewMedicaments.refresh();
    }

    public void btnMedAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MedicamentWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Medicament add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            MedicamentController controller =  fxmlLoader.getController();
            controller.setService(medicamentService);
            stage.showAndWait();
            medicaments.clear();
            medicaments.addAll(medicamentService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Medicament add.", e);
        }
    }

    public void btnMedDeleteClick(ActionEvent actionEvent) {
        Medicament selected = (Medicament)tableViewMedicaments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                medicamentService.remove(selected.getId());
                medicaments.clear();
                medicaments.addAll(medicamentService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }


    public void editClientFirstName(TableColumn.CellEditEvent cellEditEvent) {
        Client editedClient = (Client)cellEditEvent.getRowValue();
        try {
            String newFName = (String)cellEditEvent.getNewValue();
            clientService.add(editedClient.getId(),newFName, editedClient.getLastName(), editedClient.getCNP(),editedClient.getDateOfRegistration(),editedClient.getDateOfBirth());
            editedClient.setFirstName(newFName);

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        tableViewClients.refresh();
    }

    public void btnClAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 700, 200);
            Stage stage = new Stage();
            stage.setTitle("Client add");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientController clientAddController =  fxmlLoader.getController();
            clientAddController.setService(clientService);
            stage.showAndWait();
            clients.clear();
            clients.addAll(clientService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Client add.", e);
        }
    }



    public void btnClCancelClick(ActionEvent actionEvent) {
        Client selected = (Client)tableViewClients.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                clientService.remove(selected.getId());
                clients.clear();
                clients.addAll(clientService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

}