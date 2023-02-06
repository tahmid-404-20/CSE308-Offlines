package subject;

import observer.Observer;
import util.ServerState;

import java.util.Scanner;

public class ServerABC extends Subject {
    private String companyName;
    private String allyName;
    private ServerState currentState;
    private ServerState previousState;

    public ServerABC(String companyName, String allyName) {
        super();
        this.companyName = companyName;
        this.allyName = allyName;
        this.currentState = ServerState.Operational;
        this.previousState = null;
        displayServerCompany();
        displayServerState();
    }

    public void changeServerState() {
        displayServerCompany();
        Scanner scr = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Select any of the following states");
                System.out.println("1. " + ServerState.Operational.getStateDescription());
                System.out.println("2. " + ServerState.PartiallyDown.getStateDescription());
                System.out.println("3. " + ServerState.FullyDown.getStateDescription());
                System.out.print("Enter an integer between 1 and 3: ");

                int n = Integer.parseInt(scr.nextLine());

                if (n == 1) {
                    changeState(ServerState.Operational);
                } else if (n == 2) {
                    changeState(ServerState.PartiallyDown);
                } else if (n == 3) {
                    changeState(ServerState.FullyDown);
                } else {
                    System.out.println("Enter an integer between 1,2 and 3!!");
                    continue;
                }

                break;
            } catch (Exception e) {
                System.out.println("Input must be an integer!!");
            }
        }
    }

    // helpers
    private void changeState(ServerState newState) {
        if (currentState == newState) {
            System.out.println("Server is already in " + currentState.getStateDescription() + " state. No change occurs ");
        } else {
            previousState = currentState;
            currentState = newState;
            System.out.println("State change occurs in server...");
            displayServerState();
            notifyObservers();
        }
    }

    private void displayServerState() {
        System.out.println("Server is now " + currentState.getStateDescription());
    }

    private void displayServerCompany() {
        System.out.println();
        System.out.println(companyName + " Company: ");
    }

    // getters
    public ServerState getCurrentState() {
        return currentState;
    }

    public ServerState getPreviousState() {
        return previousState;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAllyName() {
        return allyName;
    }

    // overridden methods
    @Override
    public boolean addObserver(Observer o) {
        if (currentState != ServerState.Operational) {
            return false;
        } else {
            observers.add(o);
            return true;
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    protected void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
