public class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category; // e.g., appetizer, main course, dessert

    public MenuItem(int id, String name, String description, double price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " - " + description + " ($" + price + ")";
    }
}