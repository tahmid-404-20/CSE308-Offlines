package observer;

import subject.Subject;
import util.ClientStatus;
import util.ServerState;

import java.util.Random;
import java.util.Scanner;

public class RegularUser extends Observer{

    public RegularUser(int id) {
        super(id);
        userType = "Regular User";
    }

    @Override
    public void update() {
        Scanner scr = new Scanner(System.in);

        ServerState currentState = server.getCurrentState();
        ServerState previousState = server.getPreviousState();

        displayUserType();
        displayServerSituation(currentState);

        if(currentState == ServerState.Operational) {
            if(status == ClientStatus.ServiceFromAlly) {
                Random random = new Random();
                System.out.println("You need to pay " + random.nextInt(100) + "$ for using uninterrupted service");
            }
            status = ClientStatus.FullService;
        } else if(currentState == ServerState.PartiallyDown) {
            if(previousState == ServerState.Operational) {
                while(true) {
                    try{
                        System.out.println("Would you like to -");
                        System.out.println("1. Receive limited service from " + server.getCompanyName() + " server?");
                        System.out.println("2. Receive full service from " + server.getAllyName() + " server?" +
                                "(You need to pay 20$/hour for uninterrupted service)");
                        System.out.print("Enter 1 or 2 to make your choice: ");

                        int choice = Integer.parseInt(scr.nextLine());

                        if(choice == 1) {
                            status = ClientStatus.LimitedService;
                        } else if(choice == 2) {
                            System.out.println("Copying all your data to " + server.getAllyName() + " server...");
                            status = ClientStatus.ServiceFromAlly;
                        } else {
                            System.out.println("Enter 1 or 2!!");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Choice must be an integer!");
                    }
                }
            } else { // previous fully down
                if(status == ClientStatus.Unresponsive) {
                    status = ClientStatus.LimitedService;
                }
            }

        } else if(currentState == ServerState.FullyDown) {
            if(previousState == ServerState.Operational) {
                while(true) {
                    try{
                        System.out.println("Would you like to -");
                        System.out.println("1. Temporarily not receive any service from " + server.getCompanyName() + " server?");
                        System.out.println("2. Receive full service from " + server.getAllyName() + " server?" +
                                "(You need to pay 20$/hour for uninterrupted service)");
                        System.out.print("Enter 1 or 2 to make your choice: ");

                        int choice = Integer.parseInt(scr.nextLine());

                        if(choice == 1) {
                            status = ClientStatus.Unresponsive;
                        } else if(choice == 2) {
                            System.out.println("Copying all your data to " + server.getAllyName() + " server...");
                            status = ClientStatus.ServiceFromAlly;
                        } else {
                            System.out.println("Enter 1 or 2!!");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Choice must be an integer!");
                    }
                }
            } else { // partially operational
                if(status == ClientStatus.LimitedService) {
                    status = ClientStatus.Unresponsive;
                }
            }
        } else {
            System.out.println("Undefined State");
        }

        displayCurrentStatusMsg();
    }
}
