package UI;

import Domain.Client;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class ClientController {

    @FXML
    public TableView tableViewClients;
    public TableColumn tableColumnClId;
    public TableColumn tableColumnClFirstName;
    public TableColumn tableColumnClLastName;
    public TableColumn tableColumnClCNP;
    public TableColumn tableColumnClDateOfReg;
    public TableColumn tableColumnClDateOfBirth;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtCNP;
    public TextField txtDateOfRegistration;
    public TextField txtDateOfBirth;
    public TextField txtClientSearch;
    public Button btnRemoveClient;
    public Button btnGetAllClient;
    public Button btnClientSearch;
    public Button btnUndoClient;
    public Button btnRedoClient;
    public Button btnAddClient;
    public Button btnUpdateClient;

    public Button btnClAdd;
    public  Button btnClCancel;
    public Spinner spnId;

    private ClientService clientService;
    private ObservableList<Client> client = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {

        this.clientService = clientService;
    }

    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            client.addAll(clientService.getAll());
            tableViewClients.setItems(client);
        });
    }


    public void btnClCancelClick(ActionEvent actionEvent){
        Stage stage = (Stage)btnClCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClientClick(ActionEvent actionEvent){

        try{
            Client c = upsertClick();
            clientService.add(c.getId(),c.getFirstName(),c.getLastName(),c.getCNP(),c.getDateOfRegistration(),c.getDateOfBirth());
            btnClCancelClick(actionEvent);

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnRemoveClientClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());

            clientService.remove(id);
            client.clear();
            client.addAll(clientService.getAll());

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    private Client upsertClick(){
        try{
            String id = String.valueOf(spnId.getValue());
            String fName = txtFirstName.getText();
            String lName = txtLastName.getText();
            String cnp = txtCNP.getText();
            String dateOfReg = txtDateOfRegistration.getText();
            String dateOfBirth = txtDateOfBirth.getText();

            return new Client(id,fName,lName,cnp,dateOfReg,dateOfBirth);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
        return null;
    }


    /**
     *
     * @param actionEvent
     */
    public void btnSearchClient(ActionEvent actionEvent) {
        try {
            String option = txtClientSearch.getText();
            List<Client> foundClients = clientService.fullTextClientSearch(option);
            client.clear();
            client.addAll(foundClients);
            txtClientSearch.clear();
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUndoClientClick(ActionEvent actionEvent) {
        clientService.undo();
        client.clear();
        client.addAll(clientService.getAll());
    }

    public void btnRedoClientClick(ActionEvent actionEvent) {
        clientService.redo();
        client.clear();
        client.addAll(clientService.getAll());
    }
}