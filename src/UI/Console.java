package UI;

import Domain.Medicament;
import Domain.Transaction;
import Service.*;

import java.util.Scanner;

public class Console {


    private MedicamentService medicamentService;
    private ClientService clientService;
    private TransactionService transactionService;

    private Scanner scanner;

    public Console(MedicamentService medicamentService, ClientService clientService, TransactionService transactionService) {
        this.medicamentService = medicamentService;
        this.clientService = clientService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Medicament CRUD");
        System.out.println("2. Client CRUD");
        System.out.println("3. Transaction CRUD");
        System.out.println("4. Full text search");
        System.out.println("5. Display all medicaments desc by sold items");
        System.out.println("6. Display all med with price increased");
        System.out.println("7. Display all client card desc by price reduced obtained ");
        System.out.println("x. Exit");

    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runMedicamentCrud();
                    break;
                case "2":
                    runClientCrud();
                    break;
                case "3":
                    runTransactionCrud();
                    break;
                case "4":
                    fulltextSearch();
                    break;
                case "5":
                    displayMedBySoldNr();
                    break;
                case "6":
                    displayMedIncreasedPrice();
                    break;
                case "7":
                    displayCardClientByPriceRedObt();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void displayCardClientByPriceRedObt() {
        System.out.println("Display all client card desc");
        for(Object ccbpro: clientService.sortDesc()){
            System.out.println(ccbpro);
        }
    }

    private void displayMedIncreasedPrice() {
        System.out.println("Give the procent: ");
        double procent = scanner.nextDouble();
        System.out.println("Give the price:");
        double priceMed = scanner.nextDouble();

        for(Medicament m: medicamentService.increaseMedPrice(procent,priceMed)){
            System.out.println(m);
        }
    }

    private void displayMedBySoldNr() {
        System.out.println("Display all medicaments desc by sold item nr");
        for(MedicamentBySoldNrVM mVM: medicamentService.sortDesc()){
            System.out.println(mVM);
        }
    }

    private void fulltextSearch() {
        System.out.println("Are you searching for:");
        String text = scanner.nextLine();
        System.out.println("\nSearch results: ");
        for (Object c : clientService.fullTextClientSearch(text)) {
            System.out.println(c);
        }
        for (Medicament m: medicamentService.fullTextMedicamentSearch(text)){
            System.out.println(m);
        }
    }

    private void runMedicamentCrud() {
        while (true) {
            System.out.println("1. Add or update a medicament");
            System.out.println("2. Remove a medicament");
            System.out.println("3. View all medicament");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateMedicament();
                    break;
                case "2":
                    handleRemoveMedicament();
                    break;
                case "3":
                    handleViewMedicament();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewTransInterval(){
        try {
            String date1, date2;
            System.out.println("Add the start date (dd.mm.yyyy)");
            date1 = scanner.nextLine();
            System.out.println("Add the end date (dd.mm.yyyy)");
            date2 = scanner.nextLine();

            for (Transaction t: transactionService.getAllTransInterval(date1, date2)) {
                System.out.println(t);
            }
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleViewTransIntervalDelete(){
        //DeleteAllTransInterval
        try {
            String date1, date2;
            System.out.println("Add the start date (dd.mm.yyyy)");
            date1 = scanner.nextLine();
            System.out.println("Add the end date (dd.mm.yyyy)");
            date2 = scanner.nextLine();

            transactionService.deleteAllTransInterval(date1, date2);
            System.out.println("Removed");

        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateMedicament() {
        //String id, String name, String manufacturer, double price, boolean needRecipe
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter name (empty to not change for update): ");
            String name = scanner.nextLine();
            System.out.print("Enter manufacturer (empty to not change for update): ");
            String manufacturer = scanner.nextLine();
            System.out.print("Enter price (0 to not change for update): ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter if need recipe (true / false): ");
            boolean needRecipe = Boolean.parseBoolean(scanner.nextLine());

            medicamentService.addOrUpdate(id, name, manufacturer, price, needRecipe);

            System.out.println("Medicament added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleRemoveMedicament() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            medicamentService.remove(id);

            System.out.println("Medicament removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleViewMedicament() {
        for (Medicament medicament : medicamentService.getAll()) {
            System.out.println(medicament);
        }
    }

    private void runClientCrud() {
        while (true) {
            System.out.println("1. Add or update a client");
            System.out.println("2. Remove a client");
            System.out.println("3. View all clients");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateClient();
                    break;
                case "2":
                    handleRemoveClient();
                    break;
                case "3":
                    handleViewClients();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewClients() {
        for (Object client : clientService.getAll()) {
            System.out.println(client);
        }
    }

    private void handleRemoveClient() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            clientService.remove(id);

            System.out.println("Client removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateClient() {
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter first name (empty to not change for update): ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name (empty to not change for update): ");
            String lastName = scanner.nextLine();
            System.out.print("Enter CNP (empty to not change for update): ");
            String CNP = scanner.nextLine();
            System.out.print("Enter date of registration (empty to not change for update): ");
            String dateOfRegistration = scanner.nextLine();
            System.out.print("Enter date of birth (dd.mm.yyyy) :");
            String dayOfBirth = scanner.nextLine();

            //String id, String firstName, String lastName, String CNP, String dateOfRegistration, Date dateOfBirth
            clientService.add(id, firstName, lastName, CNP, dateOfRegistration, dayOfBirth);

            System.out.println("Client added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void runTransactionCrud() {
        while (true) {
            System.out.println("1. Add or update a transaction");
            System.out.println("2. Remove a transaction");
            System.out.println("3. View all transactions");
            System.out.println("4. View all transaction between 2 date");
            System.out.println("a. View all transaction with price higher than avg");
            System.out.println("b. Delete all transaction between 2 date");
            System.out.println("5. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateTransaction();
                    break;
                case "2":
                    handleRemoveTransaction();
                    break;
                case "3":
                    handleViewTransactions();
                    break;
                case "4":
                    handleViewTransInterval();
                    break;
                case "a":
                    handleViewTransAvg();
                    break;
                case "b":
                    handleViewTransIntervalDelete();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewTransAvg() {
        for(Transaction t : transactionService.getTransactionAvg()){
            System.out.println(t);
        }
    }

    private void handleViewTransactions() {
        for (Transaction transaction : transactionService.getAll()) {
            System.out.println(transaction);
        }
    }

    private void handleRemoveTransaction() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            transactionService.remove(id);

            System.out.println("Transaction removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
    //String id, String idMedicament, String idCardClient, int nrOfItems, String date, String time, double discount
    private void handleAddUpdateTransaction() {
        try {
            System.out.print("Enter id: ");
            String id = scanner.nextLine();
            System.out.print("Enter medicament id (empty to not change for update): ");
            String idMedicament = scanner.nextLine();
            System.out.print("Enter client card (empty to not change for update): ");
            String idClientCard = scanner.nextLine();
            System.out.print("Enter number of items (0 to not change for update): ");
            int numberOfItems = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter date (empty to not change for update): ");
            String date = scanner.nextLine();
            System.out.print("Enter time (empty to not change for update): ");
            String time = scanner.nextLine();

            Transaction transaction = transactionService.addOrUpdate(id, idMedicament, idClientCard, numberOfItems, date, time);
            System.out.println(String.format("Added transaction id=%s, paid price=%f, discount=%f%%", transaction.getId(), medicamentService.getPriceByID(idMedicament)*numberOfItems*(1-transaction.getDiscount()), medicamentService.getPriceByID(idMedicament)*numberOfItems*transaction.getDiscount()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

}