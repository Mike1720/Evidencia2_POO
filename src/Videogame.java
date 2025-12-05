public class Videogame extends Product {

    // DECLARACIÓN DE ATRIBUTOS DE LA CLASE HIJA
    private String style;
    private String platform;

    public Videogame(String productName, double rentalCost, int daysRented, byte availability, String style, String platform) {
        // ATRIBUTOS HEREDADOS POR LA SUPERCLASE
        super(productName, rentalCost, daysRented, availability);
        this.style = style;
        this.platform = platform;
    }

    // GETTERS & SETTERS PROPIOS DE LA CLASE
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    // SOBREESCRITURA DEL MÉTODO toString() DE LA SUPERCLASE
    @Override
    public String toString() {
        return "[VIDEOJUEGO] " + super.toString() +
                " | Estilo: " + style +
                " | Plataforma: " + platform;
    }
}
