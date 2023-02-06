package observer;

import subject.ServerABC;
import subject.Subject;
import util.ClientStatus;
import util.ServerState;

/*
This is basically abstract User class. For clarity about the pattern, using the name Observer
We are using some properties that are common to both type of users
 */
public abstract class Observer {
    protected int id;
    protected ServerABC server;
    protected ClientStatus status;
    protected String userType;

    public Observer(int id) {
        this.id = id;
    }

    public boolean subscribeToServer(ServerABC server) {
        if(server.addObserver(this)) {
            this.server = server;
            /* Status: receiving response from ABC server. Can't register if server is not operational */
            status = ClientStatus.FullService;
            displayUserType();
            System.out.println("Connected to " + server.getCompanyName() + " server");
            displayCurrentStatusMsg();
            return true;
        } else {
            System.out.println("Sorry, server is not operational");
            return false;
        }
    }

    protected void displayUserType() {
        System.out.println();
        System.out.println(userType + " " + id + ": ");
    }

    protected void displayServerSituation(ServerState serverCurrentState) {
        System.out.println(server.getCompanyName() + " server is now " + serverCurrentState.getStateDescription());
    }

    protected  void displayCurrentStatusMsg() {
        String companyName = server.getCompanyName();
        String allyName = server.getAllyName();

        if(status == ClientStatus.FullService) {
            System.out.println("Service is being provided by " + companyName + " company");
        } else if(status == ClientStatus.LimitedService) {
            // for regular users
            System.out.println("Limited service is being provided by " + companyName + " company");
        } else if(status == ClientStatus.ServiceFromTwo) {
            // for premium users
            System.out.println("Service is being provided two servers " + companyName + " and " + allyName + " company");
        } else if(status == ClientStatus.ServiceFromAlly) {
            System.out.println("Service is being provided by " + allyName + " company");
        } else if(status == ClientStatus.Unresponsive) {
            // for regular user
            System.out.println(companyName + " server is temporarily out of service. Wait until service resumes");
        } else {
            System.out.println("Undefined status");
        }

    }

    public abstract void update();
}
