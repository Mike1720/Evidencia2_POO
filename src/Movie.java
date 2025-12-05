public class Movie extends Product {

    // ATRIBUTOS DE LA CLASE MOVIE
    private String genre;
    private int productionYear;

    public Movie(String productName, double rentalCost, int daysRented, byte availability, String genre, int productionYear) {
        super(productName, rentalCost, daysRented, availability);
        this.genre = genre;
        this.productionYear = productionYear;
    }

    // GETTERS & SETTERS PROPIOS DE LA CLASE
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "[PELÍCULA] " + super.toString() +
                " | Género: " + genre +
                " | Año: " + productionYear;
    }
}
