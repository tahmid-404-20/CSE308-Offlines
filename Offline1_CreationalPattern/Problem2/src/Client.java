import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a continent among the following to get a car-");
            System.out.println("\t1. Asia");
            System.out.println("\t2. Europe");
            System.out.println("\t3. United States");
            System.out.print("Press 1, 2 or 3: ");

            try {
                int n = Integer.parseInt(scr.nextLine());
                System.out.println();
                if (n < 1 || n > 3) {
                    System.out.println("Input must be in between 1 and 3");
                } else {
                    System.out.println("Best of luck for your new Car!! The details - ");
                    Car car;
                    if (n == 1) {
                        car = CarFactory.getCarBasedOnContinent("Asia");
                    } else if (n == 2) {
                        car = CarFactory.getCarBasedOnContinent("Europe");
                    } else {
                        car = CarFactory.getCarBasedOnContinent("United States");
                    }

                    if(car!= null) {
                        car.printDetails();
                    } else {
                        System.out.println("Sorry, no car available at the given continent");
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("Input must be an Integer!!!!!");
            }

        }

    }
}
