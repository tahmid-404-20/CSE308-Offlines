import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeTong {
    public static void printMenu() {
        System.out.println("1. Americano");
        System.out.println("2. Espresso");
        System.out.println("3. Cappuccino");
        System.out.println("4. Mocha");
    }

    public static void main(String[] args) {
        List<Component> coffeeList = new ArrayList<>();
        List<String> coffeeNameList = new ArrayList<>();

        Scanner scr = new Scanner(System.in);

        System.out.println("Welcome to Coffee Tong!");
        System.out.println("Please choose your coffee:");
        while (true) {
            printMenu();
            System.out.print("Enter a number between 1 and 4 (Enter 0 to exit): ");
            int choice;
            try{
                choice = scr.nextInt();

                if(choice == 0)
                    break;

                System.out.print("Enter the number of cups: ");
                int numberOfCups = scr.nextInt();

                switch (choice) {
                    case 1:
                        Component coffee = new BlackCoffee();
                        coffee = new CoffeeGrind(coffee);

                        for(int i = 0; i < numberOfCups; i++) {
                            coffeeList.add(coffeeList.size(), coffee);
                            coffeeNameList.add(coffeeNameList.size(), CoffeeNames.AMERICANO.getName());
                        }
                        break;
                    case 2:
                        coffee = new BlackCoffee();
                        coffee = new DairyCream(coffee);
                        for(int i = 0; i < numberOfCups; i++) {
                            coffeeList.add(coffeeList.size(), coffee);
                            coffeeNameList.add(coffeeNameList.size(), CoffeeNames.ESPRESSO.getName());
                        }
                        break;

                    case 3:
                        coffee = new MilkCoffee();
                        coffee = new CinnamonPowder(coffee);
                        for(int i = 0; i < numberOfCups; i++) {
                            coffeeList.add(coffeeList.size(), coffee);
                            coffeeNameList.add(coffeeNameList.size(), CoffeeNames.CAPPUCCINO.getName());
                        }
                        break;
                    case 4:
                        coffee = new MilkCoffee();
                        coffee = new ChocolateSauce(coffee);
                        for(int i = 0; i < numberOfCups; i++) {
                            coffeeList.add(coffeeList.size(), coffee);
                            coffeeNameList.add(coffeeNameList.size(), CoffeeNames.MOCHA.getName());
                        }
                        break;
                    default:
                        System.out.println("Please enter a number between 1 and 4!");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println();
            System.out.println("Coffee(s) added to your order!");
            System.out.println("Would you like to choose another one?");
        }

        System.out.println("Thank you for your order!");
        System.out.println("Your order is:");
        long price = 0;
        for(int i = 0; i < coffeeList.size(); i++) {
            System.out.println((i+1)+ ". " + coffeeNameList.get(i) + ": " + coffeeList.get(i).getDescription() + " - " + coffeeList.get(i).getPrice() + "/-");
            price += coffeeList.get(i).getPrice();
        }

        System.out.println("Total price: " + price);
    }
}
