package machine;

public abstract class State {
    abstract boolean acceptMoney(double amount);
    abstract void releaseMoney();
    abstract boolean dispenseProduct();
    abstract void refill(int count);

    // this methods are used in two or more concrete classes
    protected void purchaseProductMsg() {
        System.out.println("Releasing the product. Collect product from the box!");
    }
    protected void providedLessMoneyMsg(double currentMoney, double productPrice) {
        System.out.println("You haven't provided enough money. You provided "+ currentMoney + "/- product is "
        + productPrice + " /-" );
    }

    protected void providedMoreMoneyMsg() {
        System.out.println("You have provided more than enough money");
    }

    protected void cannotRefillMsg() {
        System.out.println("Cannot refill now");
    }

    protected void cannotReleaseMoneyMsg() {
        System.out.println("Sorry, not enough money to release some!");
    }

}

class NoMoney extends State{

    VendingMachine vendingMachine;

    public NoMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    boolean acceptMoney(double amount) {
        vendingMachine.setCurrentMoney(vendingMachine.getCurrentMoney() + amount);

        if(Double.compare(vendingMachine.getCurrentMoney(),vendingMachine.getProductPrice()) == 0) {
            purchaseProductMsg();
            vendingMachine.setCurrentState(vendingMachine.getProductSoldState());
            return true;
        } else if(Double.compare(vendingMachine.getCurrentMoney(),vendingMachine.getProductPrice()) < 0) {
            providedLessMoneyMsg(vendingMachine.getCurrentMoney(), vendingMachine.getProductPrice());
            vendingMachine.setCurrentState(vendingMachine.getLessMoneyState());
        } else {
            providedMoreMoneyMsg();
            vendingMachine.setCurrentState(vendingMachine.getMoreMoneyState());
            return true;
        }

        return false;
    }

    @Override
    void releaseMoney() {
        cannotReleaseMoneyMsg();
    }

    @Override
    boolean dispenseProduct() {
        System.out.println("Pay first to dispense a product!!");
        return false;
    }

    @Override
    void refill(int count) {
        cannotRefillMsg();
    }
}
class LessMoney extends State{
    VendingMachine vendingMachine;

    public LessMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    boolean acceptMoney(double amount) {
        vendingMachine.setCurrentMoney(vendingMachine.getCurrentMoney() + amount);

        if(Double.compare(vendingMachine.getCurrentMoney(),vendingMachine.getProductPrice()) == 0) {
            purchaseProductMsg();
            vendingMachine.setCurrentState(vendingMachine.getProductSoldState());
            return true;
        } else if(Double.compare(vendingMachine.getCurrentMoney(),vendingMachine.getProductPrice()) < 0) {
            providedLessMoneyMsg(vendingMachine.getCurrentMoney(), vendingMachine.getProductPrice());
            vendingMachine.setCurrentState(vendingMachine.getLessMoneyState());
        } else {
            providedMoreMoneyMsg();
            vendingMachine.setCurrentState(vendingMachine.getMoreMoneyState());
            return true;
        }

        return false;
    }

    @Override
    void releaseMoney() {
        cannotReleaseMoneyMsg();
    }

    @Override
    boolean dispenseProduct() {
        System.out.println("Not enough money to dispense a product");
        return false;
    }

    @Override
    void refill(int count) {
        cannotRefillMsg();
    }
}

class MoreMoney extends State{

    VendingMachine vendingMachine;

    public MoreMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    boolean acceptMoney(double amount) {
        System.out.println("You already provided more than enough money!");
        return false;
    }

    @Override
    void releaseMoney() {
        System.out.println("Receive your extra " + (vendingMachine.getCurrentMoney() -
                vendingMachine.getProductPrice())+  "/-");
        vendingMachine.setCurrentMoney(vendingMachine.getProductPrice());
        purchaseProductMsg();
        vendingMachine.setCurrentState(vendingMachine.getProductSoldState());
    }

    @Override
    boolean dispenseProduct() {
        System.out.println("Let us release your money first!!");
        return false;
    }

    @Override
    void refill(int count) {
        cannotRefillMsg();
    }
}

class ProductSold extends State{

    VendingMachine vendingMachine;

    public ProductSold(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    boolean acceptMoney(double amount) {
        System.out.println("Let us dispense the product first!!");
        return false;
    }

    @Override
    void releaseMoney() {
        // do nothing
    }

    @Override
    boolean dispenseProduct() {
        vendingMachine.setProductCount(vendingMachine.getProductCount() - 1);
        vendingMachine.setCurrentMoney(0.0);

        if(vendingMachine.getProductCount() > 0) {
            vendingMachine.setCurrentState(vendingMachine.getNoMoneyState());
            return false;
        } else {
            System.out.println("We are out of stock now!");
            vendingMachine.setCurrentState(vendingMachine.getOutOfStockState());
            return true;
        }
    }

    @Override
    void refill(int count) {
        cannotRefillMsg();
    }
}

class OutOfStock extends State{

    VendingMachine vendingMachine;

    public OutOfStock(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    boolean acceptMoney(double amount) {
        System.out.println("Sorry we are out of stock! Cannot receive money. Come back later!");
        vendingMachine.refillOption();
        return false;
    }

    @Override
    void releaseMoney() {
        cannotReleaseMoneyMsg();
    }

    @Override
    boolean dispenseProduct() {
        System.out.println("Sorry, we are out of stock! Cannot dispense a product");
        vendingMachine.refillOption();
        return false;
    }

    @Override
    void refill(int count) {
        System.out.println("Refilled the vending machine");
        vendingMachine.setProductCount(vendingMachine.getProductCount() + count);
        vendingMachine.setCurrentState(vendingMachine.getNoMoneyState());
    }
}
