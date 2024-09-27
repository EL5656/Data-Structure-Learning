package org.example.fastfoodrestaurant;

import java.util.Scanner;

public class Order {
    private Food[] food;
    private Food[] orderedFood;
    private String foodId;
    private int orderCount;
    private double totAmount;

    public Order() {
        food = Menu.initializeMenu(); // Assuming Menu.initializeMenu() returns an array of Food items
        orderedFood = new Food[0];  // Initialize as an empty array
        orderCount = 0;
        totAmount = 0.0;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("Id\tItem\t\t\tPrice");
        for (int i = 0; i < food.length; i++) {
            System.out.print(food[i]);
        }
    }

    // Find a food item from the menu
    public Food findFoodFromMenu(String foodId) {
        for (int i = 0; i < food.length; i++) {
            if (food[i].getProdID().equalsIgnoreCase(foodId)) {
                return food[i];
            }
        }
        return null;
    }

    // Add food to the order
    public void addFoodToOrder(String foodId, int actualQuantity) {
        Food selectedFood = findFoodFromMenu(foodId);

        if (selectedFood != null) {
            for (int i = 0; i < orderCount; i++) {
                if (orderedFood[i].getProdID().equals(foodId)) {
                    System.out.println(selectedFood.getName() + " has already been added to the order.");
                    return;
                }
            }

            Food[] newArray = new Food[orderCount + 1];
            for (int i = 0; i < orderCount; i++) {
                newArray[i] = orderedFood[i];
            }
            newArray[orderCount] = selectedFood;
            orderedFood = newArray;
            orderCount++;

            double itemTotal = selectedFood.calculateProdPrice(actualQuantity);
            totAmount += itemTotal;

            if (selectedFood.getProdCalMethod().equals("100g")) {
                selectedFood.setGram(actualQuantity);
            } else {
                selectedFood.setAmount(actualQuantity);
            }

            System.out.println(selectedFood.getName() + " added to the order. Item Total: "
                    + String.format("%.2f", itemTotal) + " | Total Amount: " + String.format("%.2f", totAmount) + "\n");
        } else {
            System.out.println("Food item with ID " + foodId + " not found.");
        }
    }

    // todo - modify the display
    // Display the current order and total amount
    public void displayOrder() {
        if (orderCount == 0) {
            System.out.println("No items in the order.");
            return;
        }

        // Adjusted header with wider columns
        System.out.printf("%-5s %-30s %10s%n", "Qty", "Item", "Price");

        double totalOrderAmount = 0; // For calculating total

        for (int i = 0; i < orderedFood.length; i++) {
            Food item = orderedFood[i];

            if (item == null) {
                continue; // Skip null entries (i.e., deleted items)
            }

            int quantity = item.getProdCalMethod().equals("100g") ? item.getGram() : item.getAmount();
            double itemTotal = item.calculateProdPrice(quantity);

            // Adjust columns for the item name and price
            System.out.printf("%-5d %-30s %10.2f%n", quantity, item.getName(), itemTotal);
            totalOrderAmount += itemTotal;
        }

        // Footer with total amount aligned properly
        System.out.println("-----------------------------------------------");
        System.out.printf("%35s %10.2f%n", "Total", totalOrderAmount);
    }

    // Edit the quantity of a food item in the order
    public void editOrder(String foodId, int newQuantity) {
        for (int i = 0; i < orderCount; i++) {
            if (orderedFood[i].getProdID().equals(foodId)) {
                Food selectedFood = orderedFood[i];
                int currentQuantity = selectedFood.getProdCalMethod().equals("100g") ? selectedFood.getGram() : selectedFood.getAmount();
                double oldTotal = selectedFood.calculateProdPrice(currentQuantity);

                // Update the quantity and calculate new totals
                if (selectedFood.getProdCalMethod().equals("100g")) {
                    selectedFood.setGram(newQuantity);
                } else {
                    selectedFood.setAmount(newQuantity);
                }
                double newTotal = selectedFood.calculateProdPrice(newQuantity);

                // Update the total amount
                totAmount -= oldTotal;
                totAmount += newTotal;

                System.out.println(selectedFood.getName() + " quantity updated. New Total: "
                        + String.format("%.2f", totAmount));
                return;
            }
        }
        System.out.println("Food item with ID " + foodId + " not found in the order.");
    }

    public boolean deleteOrder(String foodId) {
        if (orderCount == 0) {
            System.out.println("No items in the order to delete.");
            return false;
        }

        // Loop through the orderedFood array to find the food item to delete
        for (int i = 0; i < orderCount; i++) {
            if (orderedFood[i].getProdID().equals(foodId)) {
                // Adjust the total amount before removing the item
                totAmount -= orderedFood[i].calculateProdPrice(orderedFood[i].getProdCalMethod().equals("100g") ? orderedFood[i].getGram():orderedFood[i].getAmount());
                orderedFood[i] = null;  // Mark item as deleted
                orderCount--; // Reduce order count
                System.out.println("Food item with ID " + foodId + " has been removed from the order.");
                return true; // Item found and deleted
            }
        }

        System.out.println("Food item with ID " + foodId + " not found in the order.");
        return false; // Item not found
    }

    public void addFood(Order order, String foodId) {
        // Create a new Scanner object inside the method
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the foodID to add food (1-8): ");
            foodId = sc.nextLine().trim(); // Get foodId from user input

            int id;
            try {
                id = Integer.parseInt(foodId);
                if (id < 1 || id > 8) {
                    throw new NumberFormatException(); // Force invalid input for out of range
                }
            } catch (NumberFormatException e) {
                System.out.println("[Invalid] Please enter a valid number between 1 and 8");
                continue; // Restart the loop for valid foodId
            }

            // If valid, proceed to ask for food amount
            System.out.print("Enter amount of food: ");
            int foodAmount;

            try {
                foodAmount = sc.nextInt();
                sc.nextLine(); // Clear the newline character from the buffer
            } catch (Exception e) {
                System.out.println("[Invalid] Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            // Add food to order
            order.addFoodToOrder(foodId, foodAmount);

            // Ask if the user wants to add more food
            System.out.print("Do you want to add more food? (y/n): ");
            String response = sc.nextLine().trim().toLowerCase();

            while (!response.equals("y") && !response.equals("n")) {
                System.out.print("[Invalid] Please enter 'y' or 'n': ");
                response = sc.nextLine().trim().toLowerCase();
            }

            if (response.equals("n")) {
                break; // Exit loop if user does not want to add more food
            }
        }
    }

    public static void main(String[] args) {
        Order order = new Order(); // Initialize order
        order.displayMenu(); // Display the menu

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Press 0 to quit");
            System.out.println("Press 1 to add item");
            System.out.println("Press 2 to edit order"); // quantity
            System.out.println("Press 3 to remove order");
            System.out.println("Press 4 to display order");

            int res = sc.nextInt();

            switch (res) {
                case 1:
                    order.addFood(order, order.foodId);
                    break;
                case 2:
                    System.out.print("Enter the food ID to edit: ");
                    order.foodId = sc.next();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = sc.nextInt();
                    order.editOrder(order.foodId, newQuantity);
                    break;
                case 3:
                    System.out.print("Enter the food ID to delete: ");
                    order.foodId = sc.next();
                    boolean deleted = order.deleteOrder(order.foodId);
                    if (deleted) {
                        System.out.println("Item successfully deleted.");
                    } else {
                        System.out.println("Failed to delete item.");
                    }
                    break;
                case 4:
                    order.displayOrder();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
    }
}
