package Service;


import Domain.Client;

public class ClientCardByPriceRedObt {
    private Client client;
    private double priceRedObtained;

    public ClientCardByPriceRedObt(Client client, double priceRedObtained) {
        this.client = client;
        this.priceRedObtained = priceRedObtained;
    }

    public Client getClient() {
        return client;
    }

    public double getPriveRedObtained() {
        return priceRedObtained;
    }

    @Override
    public String toString() {
        return "ClientCardByPriceRedObt{" +
                "client=" + client +
                ", priveRedObtained=" + priceRedObtained +
                '}';
    }
}