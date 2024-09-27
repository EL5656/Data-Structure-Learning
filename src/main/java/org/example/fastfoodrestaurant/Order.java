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

    // Display the current order and total amount
    public void displayOrder() {
        if (orderCount == 0) {
            System.out.println("No items in the order.");
            return;
        }

        System.out.printf("%-20s %10s %10s%n", "Item", "Quantity", "Price");
        double totalOrderAmount = 0; // For calculating total

        for (int i = 0; i < orderCount; i++) {
            Food item = orderedFood[i];
            int quantity = item.getProdCalMethod().equals("100g") ? item.getGram() : item.getAmount();
            double itemTotal = item.calculateProdPrice(quantity);
            System.out.printf("%-20s %10d %10.2f%n", item.getName(), quantity, itemTotal);
            totalOrderAmount += itemTotal;
        }

        System.out.println("------------------------------------------");
        System.out.printf("%-20s %10s %10.2f%n", "Total Amount", "", totalOrderAmount);
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

    public void addFood(Order order, String foodId) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the foodID to add food: ");
            foodId = sc.nextLine();
            System.out.print("Enter amount of food: ");
            int foodAmount = sc.nextInt();
            order.addFoodToOrder(foodId, foodAmount);

            System.out.print("Do you want to add more food? (y/n): ");
            String response = sc.next().trim().toLowerCase();
            if (response.equals("n")) {
                break;
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
