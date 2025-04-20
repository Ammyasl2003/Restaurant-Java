public class OrderItem {
    private MenuItem menuItem;
    private int quantity;
    private String specialInstructions;

    public OrderItem(MenuItem menuItem, int quantity, String specialInstructions) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.specialInstructions = specialInstructions;
    }

    // Getters
    public MenuItem getMenuItem() { return menuItem; }
    public int getQuantity() { return quantity; }
    public String getSpecialInstructions() { return specialInstructions; }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + menuItem.getName() + 
               (specialInstructions.isEmpty() ? "" : " (" + specialInstructions + ")") +
               " - $" + getTotalPrice();
    }
}