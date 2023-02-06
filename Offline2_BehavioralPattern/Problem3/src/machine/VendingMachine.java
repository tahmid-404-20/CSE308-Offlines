package machine;

import java.util.Scanner;

public class VendingMachine {
    private double productPrice;
    private double currentMoney;
    private int productCount;

    private State noMoneyState;
    private State lessMoneyState;
    private State moreMoneyState;
    private State productSoldState;
    private State outOfStockState;
    private State currentState;

    public VendingMachine(int productCount, double productPrice) {
        this.productCount = productCount;
        this.currentMoney = 0.0;
        this.productPrice = productPrice;

        noMoneyState = new NoMoney(this);
        lessMoneyState = new LessMoney(this);
        moreMoneyState = new MoreMoney(this);
        productSoldState = new ProductSold(this);
        outOfStockState = new OutOfStock(this);

        if(this.productCount > 0) {
            currentState = noMoneyState;
        } else {
            currentState = outOfStockState;
            if(productCount < 0)
            {
                productCount = 0;
            }
        }
    }

    public void displayDetails() {
        System.out.println("Welcome Customer!");
        System.out.println("We provide only one product now. It is of " + productPrice + "/- ");
        System.out.println("Put money and enjoy!");
    }

    public void acceptMoney(double amount) {
        if(currentState.acceptMoney(amount)) {
            currentState.releaseMoney();
            if(currentState.dispenseProduct()) {
                refillOption();
            }
        }
    }

    void refillOption() {
        Scanner scr = new Scanner(System.in);
        System.out.print("Would you like to refill? (Y/N): ");
        String response = scr.nextLine();
        if(response.startsWith("Y") || response.startsWith("y")) {
            System.out.print("Enter the count of refilled products: ");
            int count = scr.nextInt();
            currentState.refill(count);
        }
    }

    // setters
    void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    // getters
    double getProductPrice() {
        return productPrice;
    }

    double getCurrentMoney() {
        return currentMoney;
    }

    int getProductCount() {
        return productCount;
    }

    State getNoMoneyState() {
        return noMoneyState;
    }

    State getLessMoneyState() {
        return lessMoneyState;
    }

    State getMoreMoneyState() {
        return moreMoneyState;
    }

    State getProductSoldState() {
        return productSoldState;
    }

    State getOutOfStockState() {
        return outOfStockState;
    }
}
