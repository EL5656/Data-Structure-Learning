package org.example.fastfoodrestaurant;

public class Menu{
    public static Food[] initializeMenu() {
        Food[] menu = new Food[8];  // Adjust size as needed
        menu[0] = new Food("meat", "1", 5.50, 0, 100);
        menu[1] = new Food("vegetables", "2", 3.50, 0, 100);
        menu[2] = new Food("frozen packaged food", "3", 2.50, 0, 100);
        menu[3] = new Food("seafood", "4", 2.50, 1, 0);
        menu[4] = new Food("egg", "5", 2.50, 1, 0);
        menu[5] = new Food("noodle", "6", 3.50, 1, 0);
        menu[6] = new Food("rice", "7", 2.50, 1, 0);
        menu[7] = new Food("spice", "8", 2.50, 0, 100);
        return menu;
    }
}
