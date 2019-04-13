package UI;

import Domain.Medicament;
import Service.ClientService;
import Service.MedicamentService;
import Service.TransactionService;


import java.util.Scanner;

public class NewConsole {

    private MedicamentService medicamentService;
    private ClientService clientService;
    private TransactionService transactionService;

    private Scanner scanner;

    public NewConsole(MedicamentService medicamentService, ClientService clientService, TransactionService transactionService) {
        this.medicamentService = medicamentService;
        this.clientService = clientService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }


    //String id, String name, String manufacturer, double price, boolean needRecipe

    public void run() {

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();

            String felii[] = inputString.split(",");



            // medicamentService.addOrUpdate(id, name,manufacturer,price,needRecipe);
            // String id, String name, String manufacturer, double price, boolean needRecipe
            //String id, String idMedicament, String idCardClient, int nrOfItems, String date, String time)
            switch (felii[0]) {

                case "AddMedicament":
                    medicamentService.addOrUpdate(felii[1], felii[2], felii[3],Double.parseDouble(felii[4]),Boolean.parseBoolean(felii[5]));
                    System.out.println("Medicament added!");
                    break;

                case "AddCLient":
                    clientService.add(felii[1],felii[2],felii[3],felii[4],felii[5],felii[6]);
                    System.out.println("Client added!");
                    break;
                case "AddTransaction":
                    transactionService.addOrUpdate(felii[1],felii[2],felii[3],Integer.parseInt(felii[4]),felii[5],felii[6]);
                    System.out.println("Transaction added!");
                    break;
                case "ShowAllMedicament":
                    for (Medicament medicament : medicamentService.getAll()) {
                        System.out.println(medicament);
                    }
                    break;
            }



        }

    }
}