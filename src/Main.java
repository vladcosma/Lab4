import Domain.*;
import Repository.*;
import Service.ClientService;
import Service.MedicamentService;
import Service.TransactionService;
import UI.Console;
import UI.Controller;
import UI.MainController;
import UI.ManagerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/ManagerWindow.fxml"));
        Parent root = fxmlLoader.load();

        //MainController controller =  fxmlLoader.getController();
        ManagerController controller =  fxmlLoader.getController();

        IValidator<Medicament> medicamentValidator = new MedicamentValidator();
        IValidator<Client> clientValidator = new ClientValidator();
        IValidator<Transaction> transactionValidator = new TransactionValidator();

        IRepository<Medicament> medicamentRepository = new InMemoryRepository<>(medicamentValidator);
        IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
        IRepository<Transaction> transactionRepository = new InMemoryRepository<>(transactionValidator);

        MedicamentService medicamentService = new MedicamentService(medicamentRepository, transactionRepository);
        medicamentService.addOrUpdate("1","paracetamol","ZHT",85.2,false);
        medicamentService.addOrUpdate("2","fasconal","ZT",15.2,false);
        medicamentService.addOrUpdate("3","antibiotic","TFG",45.3, true);

        ClientService clientService = new ClientService(clientRepository,transactionRepository,medicamentRepository);
        clientService.add("1","Pop","Popa","1231231231231","12.03.2001","12.03.1990");
        clientService.add("2","P","Po","1231231531231","11.03.2008","10.03.1998");

        TransactionService transactionService = new TransactionService(transactionRepository, medicamentRepository);
        transactionService.addOrUpdate("1","1","2",3,"12.03.2008","12");
        transactionService.addOrUpdate("2","3","1",12,"12.03.2008","12");
        transactionService.addOrUpdate("3","2","1",2,"14.03.2008","12");
        transactionService.addOrUpdate("4","2","1",2,"15.03.2008","12");

        //controller.setServices(medicamentService, clientService, transactionService);
        controller.setServices(medicamentService,clientService);

        primaryStage.setTitle("Medicament manager");
        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.show();

        /*primaryStage.setTitle("MenuBar");
        Menu m = new Menu("Menu");
        MenuItem m1 = new MenuItem("Medicament");
        MenuItem m2 = new MenuItem("Client");
        MenuItem m3 = new MenuItem("Transaction");
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(m);
        VBox vb = new VBox(mb);
        Scene sc = new Scene(vb,500,300);
        primaryStage.setScene(sc);
        primaryStage.show();*/

        //Console console = new Console(medicamentService, clientService, transactionService);
        //console.run();

    }


    public static void main(String[] args) {
        launch(args);
    }
}