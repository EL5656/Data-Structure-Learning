package org.example.fastfoodrestaurant;
public class Food extends Product {
    private  int gram;
    public Food(String foodName, String foodID, double price, int amount, int gram) {
        super(foodName, foodID, price, amount);  // Call to the parent constructor
        this.gram = gram;
    }

    public Food(){

    }
    public int getGram() {
        return gram;
    }

    public void setGram(int gram) {
        this.gram = gram;
    }

    public String getProdCalMethod(){
        String prodCalMethod =" ";
        if(amount==0){
            prodCalMethod="100g";
        } else if (gram==0) {
            prodCalMethod="unit";
        }
        return prodCalMethod;
    }
    @Override
    public double calculateProdPrice(int actualQuantity) {
        String calcMeth = getProdCalMethod();
        if(calcMeth=="100g"){
            price = (actualQuantity/gram)*price;
        }else{
            price = actualQuantity*amount* price;
        }
        return price;
    }

    // Override toString method
    @Override
    public String toString() {
        return String.format(
                "--------------------------------------------\n" +
                        "%-4s%-20s\t\tRM%.2f/%s\n",
                super.getProdID(), super.getName(), super.getPrice(), getProdCalMethod()
        );
    }

}