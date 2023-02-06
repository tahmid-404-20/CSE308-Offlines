import java.util.Scanner;

public abstract class Builder {
    protected PC pc;

    public abstract void newPC();
    public abstract void addMotherBoard();
    public abstract void addHDD();
    public abstract void addProcessor();
    public abstract void addCooler();
    public abstract void addDVDDrive();
    final public PC getPc() {
        PC temp = this.pc;
        this.pc = null;
        return temp;
    }
    final public boolean addRAM() {
        Scanner scr = new Scanner(System.in);
        while(true) {
           try {
               System.out.println("Which of the following DDR4 RAMs do you want? ");
               System.out.println("1. 8 GB DDR4 RAM - 2666 MHz (2620 BDT)");
               System.out.println("2. 8 GB DDR4 RAM - 3200 MHz (2950 BDT)");
               System.out.println("3. Go Back");
               System.out.print("Enter 1,2 or 3: ");

               int n = Integer.parseInt(scr.nextLine());

               if(n>=1 && n<=3) {
                   if(n==1) {
                       pc.addAdditionalPart(new RAMDDR4_2666MHz());
                       return true;
                   } else if(n==2) {
                       pc.addAdditionalPart(new RAMDDR4_3200MHz());
                       return true;
                   }
                   return false;

               } else {
                   System.out.println("Input must be among 1, 2 or 3\n");
               }

           } catch (Exception e) {
               System.out.println("Input must be an integer!\n");
           }
        }
    }
    final public boolean addGraphicsCard() {
        Scanner scr = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Which of the following Graphics Card do you want? ");
                System.out.println("1. 2 GB Graphics Card (2620 BDT)");
                System.out.println("2. 4 GB Graphics Card (2950 BDT)");
                System.out.println("3. Go Back");
                System.out.print("Enter 1,2 or 3: ");

                int n = Integer.parseInt(scr.nextLine());

                if(n>=1 && n<=3) {
                    if(n==1) {
                        pc.addAdditionalPart(new GraphicsCard2GB());
                        return true;
                    } else if(n==2) {
                        pc.addAdditionalPart(new GraphicsCard4GB());
                        return true;
                    }
                    return false;

                } else {
                    System.out.println("Input must be among 1, 2 or 3\n");
                }

            } catch (Exception e) {
                System.out.println("Input must be an integer!\n");
            }
        }
    }

}
