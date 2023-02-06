package observer;

import subject.Subject;
import util.ClientStatus;
import util.ServerState;

import java.util.Scanner;

public class PremiumUser extends Observer{

    public PremiumUser(int id) {
        super(id);
        userType = "Premium User";
    }

    @Override
    public void update() {
        Scanner scr = new Scanner(System.in);

        ServerState currentState = server.getCurrentState();
        ServerState previousState = server.getPreviousState();

        displayUserType();
        displayServerSituation(currentState);

        if(currentState == ServerState.Operational) {
            status = ClientStatus.FullService;

        } else if(currentState == ServerState.PartiallyDown) {
            if(previousState == ServerState.Operational) {
                while(true) {
                    try{
                        System.out.println("Would you like to -");
                        System.out.println("1. Receive service from two server? (Partially from " + server.getCompanyName() +
                                " and " + server.getAllyName() + " server?");
                        System.out.println("2. Receive full service from " + server.getAllyName() + " server?");
                        System.out.print("Enter 1 or 2 to make your choice: ");

                        int choice = Integer.parseInt(scr.nextLine());

                        if(choice == 1) {
                            status = ClientStatus.ServiceFromTwo;
                        } else if(choice == 2) {
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
            }

        } else if(currentState == ServerState.FullyDown) { // fully down
            if(previousState == ServerState.Operational) {
                System.out.println("Shifting all your service to " + server.getAllyName() + " server...");
                status = ClientStatus.ServiceFromAlly;
            } else { // partially operational
                if(status == ClientStatus.ServiceFromTwo) {
                    System.out.println("Shifting all your service to " + server.getAllyName() + " server...");
                    status = ClientStatus.ServiceFromAlly;
                }
            }
        } else {
            System.out.println("Undefined State");
        }

        displayCurrentStatusMsg();
    }

}
