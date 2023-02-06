public class CarFactory {
    public static Car getCarBasedOnContinent(String continent) {
        if (continent == null || continent.length() == 0) {
            return null;
        } else if (continent.equalsIgnoreCase("Asia")) {
            return new Toyota();
        } else if (continent.equalsIgnoreCase("Europe")) {
            return new BMW();
        } else if (continent.equalsIgnoreCase("United States")) {
            return new Tesla();
        } else {
            return null;
        }
    }
}
