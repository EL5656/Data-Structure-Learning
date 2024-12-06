package org.example;

public class FindElement {
    public static void main(String[] args) {

        int num [] = {9,2,26,29,22};

        int largest = num[0];
        int secondLarge = num[0];

        for(int i=1; i< num.length;i++){
            if(num[i]>largest){
                secondLarge = largest;
                largest = num[i];
            } else if (num[i]>secondLarge && num[i]!=largest) {
                secondLarge = num[i];
            }
        }
        System.out.println(secondLarge);
    }
}
