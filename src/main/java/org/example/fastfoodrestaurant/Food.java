package org.example.fastfoodrestaurant;

public class Food extends Product {
    private int gram;

    public Food(String foodName, String foodID, double price, int amount, int gram) {
        super(foodName, foodID, price, amount);
        this.gram = gram;
    }

    public Food() { }

    public int getGram() {
        return gram;
    }

    public void setGram(int gram) {
        this.gram = gram;
    }

    // Determine how the product is priced (per 100g or per unit)
    public String getProdCalMethod() {
        if (amount == 0) {
            return "100g";
        } else if (gram == 0) {
            return "unit";
        }
        return "";
    }

    @Override
    public double calculateProdPrice(int actualQuantity) {
        String calcMethod = getProdCalMethod();
        double totalPrice = 0.0;

        if (calcMethod.equals("100g")) {
            // Price per 100g
            totalPrice = (actualQuantity / 100.0) * price;
        } else {
            // Price per unit
            totalPrice = actualQuantity * price;
        }

        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "--------------------------------------------\n" +
                        "%-4s%-20s\t\tRM%.2f/%s\n",
                super.getProdID(), super.getName(), super.getPrice(), getProdCalMethod()
        );
    }
}
