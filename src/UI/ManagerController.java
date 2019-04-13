package UI;

import Service.ClientService;
import Service.MedicamentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerController {
    public Button btnMedicine;
    public Button btnClient;
    public Button btnTransaction;

    private MedicamentService medicineService;
    private ClientService clientService;
    // private TransactionService transactionService;

    /*public void setServices(MedicineService medicineService, ClientService clientService, TransactionService transactionService) {
        this.medicineService = medicineService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }*/

    public void setServices(MedicamentService medicineService,ClientService clientService) {
        this.medicineService = medicineService;
        this.clientService = clientService;

    }

    /**
     *
     * @param actionEvent
     */
    public void btnMedicineClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MedicamentWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Medicine manager");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            MedicamentController controller =  fxmlLoader.getController();
            controller.setService(medicineService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }

    /**
     * @param actionEvent
     */
    public void btnClientClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Client manager");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window", e);
        }
    }
}