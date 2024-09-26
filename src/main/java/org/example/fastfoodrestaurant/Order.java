package org.example.fastfoodrestaurant;

import java.util.Scanner;

public class Order{
    private Food[] food;
    private Food[] orderedFood;
    private String foodId, item;
    private int orderCount, quantity;
    private double unitPrice, itemTotal, totAmount;
    public Order(String foodId, String item, int quantity, double unitPrice, double itemTotal, double totAmount) {
        this.foodId = foodId;
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.itemTotal = itemTotal;
        this.totAmount = totAmount;
    }

    public Order() {
        food = Menu.initializeMenu();// Assuming Menu.initializeMenu() returns an array of Food items
        orderedFood = new Food[0];  // Initialize as an empty array
        orderCount=0;
    }

    // Method to display the menu
    public void displayMenu() {
        System.out.println();
        System.out.println("Id\tItem\t\t\tPrice");
        for (int i = 0; i < food.length; i++) {
            System.out.print(food[i]);
        }
    }

    public Food findFoodFromMenu(String foodId) {
        for (int i = 0; i < food.length; i++) {
            // Check if the foodId matches the ID in the food array
            if (food[i].getProdID().equalsIgnoreCase(foodId)) {  // Using equalsIgnoreCase for case-insensitive matching
                return food[i];  // Return the food item if found
            }
        }
        // Return null if not found
        return null;
    }

    public void addFoodToOrder(String foodId, int actualQuantity) {
        // Find the food item
        Food selectedFood = findFoodFromMenu(foodId);

        if (selectedFood != null) {
            // Check if the food item has already been added
            for (int i = 0; i < orderCount; i++) {
                if (orderedFood[i].getProdID().equals(foodId)) {
                    System.out.println(selectedFood.getName() + " has already been added to the order.");
                    return; // Exit the method if the food item is already in the order
                }
            }

            // Create a new array with size incremented by 1
            Food[] newArray = new Food[orderCount + 1];

            // Copy existing items to the new array
            for (int i = 0; i < orderCount; i++) {
                newArray[i] = orderedFood[i];
            }

            // Add the selected food to the end of the new array
            newArray[orderCount] = selectedFood;

            // Update the orderedFood reference and increment orderCount
            orderedFood = newArray;
            orderCount++;

            selectedFood.setPrice(selectedFood.getPrice());
            unitPrice = selectedFood.getPrice();
            System.out.println("UP: "+unitPrice);

            String meth = selectedFood.getProdCalMethod();
            // Calculate total amount
            itemTotal = selectedFood.calculateProdPrice(actualQuantity);
            System.out.println(itemTotal);
            totAmount += itemTotal;

            if (meth.equals("100g")) {
                selectedFood.setGram(actualQuantity);
            } else {
                selectedFood.setAmount(actualQuantity);
            }

            System.out.println(selectedFood.getName() + " added to the order. " + String.format("%.2f", totAmount) + "\n");
        } else {
            System.out.println("Food item with ID " + foodId + " not found.");
        }
    }

    public void displayOrder() {
        if (orderCount == 0) {
            System.out.println("No items in the order.");
            return;
        }
        System.out.printf("%-20s %10s %10s%n", "Item", "Quantity", "Price");
        // Loop through ordered food items
        for (int i = 0; i < orderCount; i++) {
            Food item = orderedFood[i];
            String meth = item.getProdCalMethod();
            quantity = meth.equals("100g") ? item.getGram() : item.getAmount();
            System.out.printf("%-20s %10d %10.2f%n", item.getName(), quantity, itemTotal);
        }
        System.out.println("------------------------------------------");
        System.out.printf("%-20s %10s %10.2f%n", "Total Amount", "", totAmount);
    }

    public Order editOrder(String foodId, int foodQuantity) {
        // Check if there are any items in the order
        if (orderCount == 0) {
            System.out.println("No item has been added to the order yet.");
            System.out.println();
            return null; // No items to edit
        }

        // Loop through the orderedFood array to find the food by ID
        for (int i = 0; i < orderCount; i++) {
            if (orderedFood[i].getProdID().equals(foodId)) {
                Food selectedFood = orderedFood[i];
                String meth = selectedFood.getProdCalMethod();
                System.out.println("totAmount before editing: "+totAmount);
                if(meth.equals("100g")){
                    selectedFood.setGram(foodQuantity);
                }else{
                    selectedFood.setAmount(foodQuantity);
                }
                System.out.println("For debugging...");
                quantity = meth.equals("100g")?selectedFood.getGram():selectedFood.getAmount();
                System.out.println("Unit Price: "+unitPrice+"\nQuantity: " +quantity);
                itemTotal -= meth.equals("100g")?(quantity/100)*unitPrice:unitPrice*quantity;
                System.out.println(itemTotal);
                totAmount -= itemTotal;

                return new Order(selectedFood.getProdID(), selectedFood.getName(), foodQuantity, unitPrice, itemTotal, totAmount);
            }
        }

        // If the food ID was not found in the order
        System.out.println("Food item with ID " + foodId + " not found in the order.");
        return null;
    }

    public void addFood(Order order, String foodId) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the foodID to add food: ");
            foodId = sc.nextLine();
            System.out.print("Enter amount of food: ");
            int foodAmount;
            while (true) {
                try {
                    String input = sc.nextLine();
                    foodAmount = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            order.addFoodToOrder(foodId, foodAmount);
            System.out.print("Do you want to add more food? (y/n): ");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("n")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Order order = new Order(); // Initialize order
        order.displayMenu(); // Display the menu
        System.out.println();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Press 0 to quit");
            System.out.println("Press 1 to add item");
            System.out.println("Press 2 to edit order"); //quantity
            System.out.println("Press 3 to delete order"); //cancel order
            System.out.println("Press 4 to display order");

            int res = sc.nextInt();

            switch (res) {
                case 1:
                    System.out.println("add");
                    order.addFood(order, order.foodId);
                    break;
                case 2:
                    System.out.println("edit");
                    System.out.print("Enter the food ID to edit: ");
                    order.foodId = sc.next();
                    System.out.print("Enter new quantity: ");
                    order.quantity = sc.nextInt();
                    order.editOrder(order.foodId, order.quantity);
                    break;
                case 3:
                    System.out.println("delete");
                    //delete by id
                    break;
                case 4:
                    System.out.println("display");
                    order.displayOrder();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }

            if (res == 0) {
                break;
            }
        }
    }
}
