package jastajas.domowe17b;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private String picture;

    public Product() {
    }

    public Product(String name, double price, String category, String picture) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.picture = picture;
    }

    public Product(String name, double price, String category, String picture, int id) {
        this(name, price, category, picture);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + category;
    }
}
