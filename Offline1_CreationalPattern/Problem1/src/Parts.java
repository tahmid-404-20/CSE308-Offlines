public abstract class Parts {
    private Double price;

    public abstract String getDetails();

    final public Double getPrice() {
        return price;
    }
    final protected void setPrice(Double price) {
        this.price = price;
    }
}