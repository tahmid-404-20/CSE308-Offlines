public abstract class Decorator extends Component{
    Component component;

    @Override
    public String getDescription() {
        return component.getDescription() + ", " + description;
    }

    @Override
    public int getPrice() {
        return component.getPrice() + price;
    }
}

class CoffeeGrind extends Decorator {
    public CoffeeGrind(Component component) {
        this.component = component;
        description = "Coffee Grind";
        price = 30;
    }
}

class DairyCream extends Decorator {
    public DairyCream(Component component) {
        this.component = component;
        description = "Dairy Cream";
        price = 40;
    }
}

class CinnamonPowder extends Decorator {
    public CinnamonPowder(Component component) {
        this.component = component;
        description = "Cinnamon Powder";
        price = 50;
    }
}

class ChocolateSauce extends Decorator {
    public ChocolateSauce(Component component) {
        this.component = component;
        description = "Chocolate Sauce";
        price = 30;
    }
}