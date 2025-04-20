import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private Date orderDate;
    private String status; // e.g., "Pending", "Preparing", "Completed", "Cancelled"
    private List<OrderItem> items;
    private String tableNumber;

    public Order(int orderId, Customer customer, String tableNumber) {
        this.orderId = orderId;
        this.customer = customer;
        this.tableNumber = tableNumber;
        this.orderDate = new Date();
        this.status = "Pending";
        this.items = new ArrayList<>();
    }

    // Methods to manage order items
    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItem> getItems() { return items; }
    public String getTableNumber() { return tableNumber; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId).append("\n");
        sb.append("Customer: ").append(customer).append("\n");
        sb.append("Table: ").append(tableNumber).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append("  ").append(item).append("\n");
        }
        sb.append("Total: $").append(calculateTotal());
        return sb.toString();
    }
}