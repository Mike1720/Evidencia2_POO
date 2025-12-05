public class Product {

    // ABSTRACCIÓN DE LOS ATRIBUTOS GENERALES DE LOS PRODUCTOS
    protected String productName;
    protected double rentalCost;
    protected int daysRented;
    protected byte availability; // 0 = disponible, 1 = rentado

    public Product(String productName, double rentalCost, int daysRented, byte availability) {
        this.productName = productName;
        this.rentalCost = rentalCost;
        this.daysRented = daysRented;
        this.availability = availability;
    }

    // GETTERS & SETTERS PROPIOS DE LA CLASE
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public byte getAvailability() {
        return availability;
    }

    public void setAvailability(byte availability) {
        this.availability = availability;
    }

    // MÉTODO A SOBREESCRIBIR
    @Override
    public String toString() {
        return "Nombre: " + productName +
                " | Costo renta: $" + rentalCost +
                " | Días rentado: " + daysRented +
                " | Rentado: " + (availability == 1 ? "Sí" : "No");
    }
}
