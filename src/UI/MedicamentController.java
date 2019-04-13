package UI;

import Domain.Medicament;
import Service.MedicamentService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;


public class MedicamentController {

    public TableView tableViewMedicaments;
    public TableColumn tableColumnId;
    public TableColumn tableColumnName;
    public TableColumn tableColumnManufacturer;
    public TableColumn tableColumnPrice;
    public TableColumn tableColumnNeedRecipe;

    public Button btnMedAdd;
    public Button btnMedDelete;

    public TextField txtName;
    public TextField txtManufacturer;
    public TextField txtPrice;
    public CheckBox chkNeedRecipe;

    public Button btnAdd;
    public  Button btnCancel;
    public Spinner spnId;

    private MedicamentService medicamentService;
    private ObservableList<Medicament> medicine = FXCollections.observableArrayList();

    public void setService(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }


    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            medicine.addAll(medicamentService.getAll());
            tableViewMedicaments.setItems(medicine);
        });

    }
    public void btnAddMedicineClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String name = txtName.getText();
            String producer = txtManufacturer.getText();
            double price = Double.parseDouble(txtPrice.getText());
            boolean recipe = chkNeedRecipe.isSelected();

            medicamentService.addOrUpdate(id,name,producer,price,recipe);
            medicine.clear();
            medicine.addAll(medicamentService.getAll());
            spnId.getValueFactory().setValue(null);
            txtName.clear();
            txtManufacturer.clear();
            txtPrice.clear();
            chkNeedRecipe.setSelected(false);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent){
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnRemoveMedicineClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            medicamentService.remove(id);
            medicine.clear();
            medicine.addAll(medicamentService.getAll());
            //spnId.getValueFactory().setValue(null);

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnAddClick(ActionEvent actionEvent){

        try{
            String id = String.valueOf(spnId.getValue());
            String name = txtName.getText();
            String manufacturer = txtManufacturer.getText();
            double price = Double.parseDouble(txtPrice.getText());
            boolean needRecipe = chkNeedRecipe.isSelected();

            medicamentService.addOrUpdate(id,name,manufacturer,price,needRecipe);
            btnCancelClick(actionEvent);

        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUndoMedicineClick(ActionEvent actionEvent) {
        medicamentService.undo();
        medicine.clear();
        medicine.addAll(medicamentService.getAll());
    }

    public void btnRedoMedicineClick(ActionEvent actionEvent) {
        medicamentService.redo();
        medicine.clear();
        medicine.addAll(medicamentService.getAll());
    }
}