import java.util.ArrayList;
import java.util.List;

public class PC {
    private String category;
    private double basePrice;
    private List<Parts> baseParts;
    private List<Parts> fixedParts;
    private List<Parts> additionalParts;
    private double totalPrice;

    public PC(String category) {
        this.category = category;
        fixedParts = new ArrayList<>();
        additionalParts = new ArrayList<>();
        baseParts = new ArrayList<>();
        totalPrice = 0;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        totalPrice = basePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addBasePart(Parts component) {
        baseParts.add(component);
    }

    public void addFixedPart(Parts component) {
        fixedParts.add(component);
        totalPrice += component.getPrice();
    }

    public void addAdditionalPart(Parts component) {
        additionalParts.add(component);
        totalPrice += component.getPrice();
    }

    // will implement show configurations details later
    public void getDetailedPriceReasoning() {
        System.out.println("PC category: " + this.category);
        System.out.println("Base price: " + this.basePrice);

        System.out.println("Base components (Included in the base price)");
        int count = 1;
        for (var baseElement : baseParts) {
            System.out.println(count + ". " + baseElement.getDetails() + " ");
            count++;
        }

        System.out.println("Pre-installed components (exclusive of the base price)");
        count = 1;
        for (var fixedElement : fixedParts) {
            System.out.println(count + ". " + fixedElement.getDetails() + " Price: " + fixedElement.getPrice());
            count++;
        }

        count = 1;
        if(additionalParts.size() > 0) {
            System.out.println("Additional Components");
            for(var additionalElement:additionalParts) {
                System.out.println(count + ". " + additionalElement.getDetails() + " Price: " + additionalElement.getPrice());
                count++;
            }
        }
    }
}
