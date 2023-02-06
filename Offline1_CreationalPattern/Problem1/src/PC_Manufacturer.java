import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PC_Manufacturer {

    public static void provideAddOns(Director director, Builder builder, Scanner scr) {
        System.out.println("We provide some nice add Ons to upgrade each pc. You will like them!");
        while (true) {
            try {
                System.out.println("Choose among the following");
                System.out.println("1. DDR4 RAM");
                System.out.println("2. Graphics Card");
                System.out.println("3. Exit");
                System.out.print("Enter 1,2 or 3: ");

                int n = Integer.parseInt(scr.nextLine());

                if (n >= 1 && n <= 3) {
                    if (n == 1) {
                        if (director.addRAM(builder)) {
                            System.out.println("Successfully added RAM");
                            System.out.println("Want to order some more?");
                        } else {
                            System.out.println("RAM isn't being added");
                            System.out.println("You may choose again!");
                        }
                    } else if (n == 2) {
                        if (director.addGraphicsCard(builder)) {
                            System.out.println("Successfully added graphics card");
                            System.out.println("Want to order some more add Ons?");
                        } else {
                            System.out.println("Graphics card isn't being added");
                            System.out.println("You may choose again!");
                        }
                    } else {
                        return;
                    }


                } else {
                    System.out.println("Input must be among 1, 2 or 3\n");
                }

            } catch (Exception e) {
                System.out.println("Input must be an integer!\n");
            }
        }

    }

    public static void showStockSituation() {
        System.out.println("We have the following in our stock currently");
        System.out.println("1. PC1 (Intel Core i5 11th Gen - CPU Cooling System)");
        System.out.println("\t\tBase Price - 70k, Core i5 - 20k, CPU Cooler - 36k -- 126k (BDT)");
        System.out.println("2. PC2 (Intel Core i7 11th Gen - Liquid Cooling System)");
        System.out.println("\t\tBase Price - 70k, Core i7 - 37k, Liquid Cooler - 17k -- 124k (BDT)");
        System.out.println("3. PC3 (Intel Core i9 - DVD Drive)");
        System.out.println("\t\tBase Price - 70k, Core i9 - 65k, DVD Drive - 6k -- 141k (BDT)");
        System.out.println("4. Gaming PC (AMD Ryzen 7 5700X)");
        System.out.println("\t\tBase Price - 70k, AMD Ryzen 7 5700X - 28k --98k BDT");
    }

    public static void showOrderDetails(List<PC> pcs) {
        int orderNo = 1;
        double grossTotal = 0.0;
        System.out.println("No of pcs " + pcs.size());
        for (var pc : pcs) {
            System.out.println("\nPC # " + orderNo);
            pc.getDetailedPriceReasoning();
            System.out.println("Total price: " + pc.getTotalPrice());
            grossTotal += pc.getTotalPrice();
            orderNo++;
        }

        System.out.println("\nGross Total of your order: " + grossTotal);
    }

    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        System.out.println("Welcome to our PC Manufacturing System");
        showStockSituation();

        String cmd;
        while (true) {
            System.out.print("Would you like to order something? Press O to open and E to exit: ");
            cmd = scr.nextLine();

            if (!(cmd.equalsIgnoreCase("E") || cmd.equalsIgnoreCase("O"))) {
                System.out.println("Enter valid character");
            } else {
                break;
            }
        }

        while (cmd.equalsIgnoreCase("O")) {
            List<PC> pcs = new ArrayList<>();
            Director director = new Director();
            while (true) {
                System.out.print("Press 1, 2, 3 or 4 to choose a PC: ");
                try {
                    String num = scr.nextLine();
                    int n = Integer.parseInt(num);
                    if (n >= 1 && n <= 4) {
                        if (n == 1) {
                            IntelCorei5Builder intelCorei5Builder = new IntelCorei5Builder();
                            director.buildPC(intelCorei5Builder);
                            System.out.println("PC " + n + " purchased!");
                            provideAddOns(director, intelCorei5Builder, scr);
                            pcs.add(director.getPC(intelCorei5Builder));
                        } else if (n == 2) {
                            IntelCorei7Builder intelCorei7Builder = new IntelCorei7Builder();
                            director.buildPC(intelCorei7Builder);
                            System.out.println("PC " + n + " purchased!");
                            provideAddOns(director, intelCorei7Builder, scr);
                            pcs.add(director.getPC(intelCorei7Builder));
                        } else if (n == 3) {
                            IntelCorei9Builder intelCorei9Builder = new IntelCorei9Builder();
                            director.buildPC(intelCorei9Builder);
                            System.out.println("PC " + n + " purchased!");
                            provideAddOns(director, intelCorei9Builder, scr);
                            pcs.add(director.getPC(intelCorei9Builder));
                        } else {
                            GamingPCBuilder gamingPCBuilder = new GamingPCBuilder();
                            director.buildPC(gamingPCBuilder);
                            System.out.println("Gaming PC purchased!");
                            provideAddOns(director, gamingPCBuilder, scr);
                            pcs.add(director.getPC(gamingPCBuilder));
                        }

                        System.out.println("Successfully purchased a machine");
                        boolean orderAgain = false;
                        while (true) {
                            System.out.print("Would you like to purchase another one? (Y/N): ");
                            String decision = scr.nextLine();
                            if (decision.equalsIgnoreCase("Y")) {
                                showStockSituation();
                                orderAgain = true;
                                break;
                            } else if (decision.equalsIgnoreCase("N")) {
                                break;
                            } else {
                                System.out.println("Enter correct character!");
                            }
                        }

                        if (!orderAgain) {
                            break;
                        }

                    } else {
                        System.out.println("Please enter an integer in between 1 and 4!");
                    }
                } catch (Exception e) {
                    System.out.println("You must press an integer!");
                }
            }

            System.out.println("\n\nSuccessfully completed an order. Details ");
            showOrderDetails(pcs);

            while (true) {
                System.out.print("\n\nIf you want to open an order again press O, otherwise E to Exit: ");
                cmd = scr.nextLine();

                if (!(cmd.equalsIgnoreCase("E") || cmd.equalsIgnoreCase("O"))) {
                    System.out.println("Enter valid character");
                } else {
                    if (cmd.equalsIgnoreCase("O")) {
                        showStockSituation();
                    }
                    break;
                }
            }
        }
    }
}
