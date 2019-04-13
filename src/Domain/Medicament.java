package Domain;

public class Medicament extends Entity{
    //CRUD medicament: id, nume, producător, preț, necesită rețetă. Prețul să fie strict pozitiv.

    //private String id;
    private String name;
    private String manufacturer;
    private double price;
    private boolean needRecipe;

    public Medicament(String id, String name, String manufacturer, double price, boolean needRecipe) {
        super(id);
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.needRecipe = needRecipe;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                '}';
    }

    /*public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNeedRecipe() {
        return needRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        this.needRecipe = needRecipe;
    }
}