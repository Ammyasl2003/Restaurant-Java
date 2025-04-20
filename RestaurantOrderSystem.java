import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RestaurantOrderSystem {
    private List<MenuItem> menu;
    private Map<Integer, Customer> customers;
    private List<Order> orders;
    private int nextOrderId;
    private int nextCustomerId;
    private Scanner scanner;

    public RestaurantOrderSystem() {
        this.menu = new ArrayList<>();
        this.customers = new HashMap<>();
        this.orders = new ArrayList<>();
        this.nextOrderId = 1;
        this.nextCustomerId = 1;
        this.scanner = new Scanner(System.in);
        
        initializeSampleData();
        run();
    }

    // Add these missing methods:
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Order createOrder(int customerId, String tableNumber) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        Order order = new Order(nextOrderId++, customer, tableNumber);
        orders.add(order);
        return order;
    }

    public MenuItem findMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    // Rest of your existing code...
    private void initializeSampleData() {
        // Add sample menu items
        menu.add(new MenuItem(1, "Margherita Pizza", "Classic pizza with tomato sauce and mozzarella", 12.99, "Main Course"));
        menu.add(new MenuItem(2, "Caesar Salad", "Romaine lettuce with Caesar dressing and croutons", 8.99, "Appetizer"));
        menu.add(new MenuItem(3, "Chocolate Cake", "Rich chocolate cake with fudge icing", 6.99, "Dessert"));
        menu.add(new MenuItem(4, "Pasta Carbonara", "Spaghetti with creamy egg and bacon sauce", 14.99, "Main Course"));
        menu.add(new MenuItem(5, "Garlic Bread", "Toasted bread with garlic butter", 4.99, "Appetizer"));
    }

    private void run() {
        while (true) {
            System.out.println("\n=== Restaurant Order System ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place New Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    displayMenu();
                    break;
                case 2:
                    placeNewOrder();
                    break;
                case 3:
                    viewAllOrders();
                    break;
                case 4:
                    System.out.println("Thank you for using our system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.printf("%-4s %-20s %-50s %-10s %-15s\n", 
                         "ID", "Name", "Description", "Price", "Category");
        for (MenuItem item : menu) {
            System.out.printf("%-4d %-20s %-50s $%-9.2f %-15s\n", 
                            item.getId(), item.getName(), item.getDescription(), 
                            item.getPrice(), item.getCategory());
        }
    }

    private void placeNewOrder() {
        System.out.println("\n=== Place New Order ===");
        
        // Get customer information
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter your table number: ");
        String tableNumber = scanner.nextLine();
        
        // Create new customer
        Customer customer = new Customer(nextCustomerId++, name, phone, email);
        addCustomer(customer);
        
        // Create new order
        Order order = createOrder(customer.getId(), tableNumber);
        
        // Add items to order
        boolean addingItems = true;
        while (addingItems) {
            displayMenu();
            System.out.print("\nEnter menu item ID to add (0 to finish): ");
            int itemId = getIntInput();
            
            if (itemId == 0) {
                addingItems = false;
            } else {
                MenuItem item = findMenuItemById(itemId);
                if (item == null) {
                    System.out.println("Invalid item ID. Please try again.");
                } else {
                    System.out.print("Enter quantity: ");
                    int quantity = getIntInput();
                    
                    System.out.print("Any special instructions? (press Enter if none): ");
                    String instructions = scanner.nextLine();
                    
                    order.addItem(new OrderItem(item, quantity, instructions));
                    System.out.printf("Added %d x %s to your order.\n", quantity, item.getName());
                }
            }
        }
        
        // Display order confirmation
        System.out.println("\n=== Order Confirmation ===");
        System.out.println(order);
        System.out.println("Thank you for your order!");
    }

    private void viewAllOrders() {
        System.out.println("\n=== All Orders ===");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
                System.out.println("----------------------------------------");
            }
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static void main(String[] args) {
        new RestaurantOrderSystem();
    }
}