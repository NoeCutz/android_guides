package practices.com.listview.model;

public class Jean {

    //region Fields
    private String name;

    private String size;

    private double price;
    //endregion


    //region Constructors
    public Jean() {
    }

    public Jean(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //endregion
}
