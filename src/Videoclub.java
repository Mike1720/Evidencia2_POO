import java.util.ArrayList;

public class Videoclub {

    private static ArrayList<Movie> movies = new ArrayList<>();
    private static ArrayList<Videogame> videogames = new ArrayList<>();

    static void main() {

        IO.println("<<=== SISTEMA ADMINISTRACIÓN VIDEOCLUB ===>>");

        boolean running = true;

        while (running) {
            IO.println("\n====== MENÚ PRINCIPAL ======");
            IO.println("A - Agregar producto");
            IO.println("T/t - Mostrar todos los productos");
            IO.println("P/p - Mostrar películas");
            IO.println("V/v - Mostrar videojuegos");
            IO.println("S/s - Verificar disponibilidad de pelicula");
            IO.println("J/j - Verificar disponibilidad de videojuego");
            IO.println("C/c - Cantidad de películas rentadas");
            IO.println("X/x - Cantidad de videojuegos en Xbox One");
            IO.println("U/u - Salir");

            IO.print("\nSelecciona una opción: ");
            String option = IO.readln();

            switch (option) {

                case "A" -> addProduct();
                case "T/t" -> showAllProducts();
                case "P/p" -> showAllMovies();
                case "V/v" -> showAllGames();
                case "S/s" -> checkMovieRented();
                case "J/j" -> checkGameRented();
                case "C/c" -> countMoviesRented();
                case "X/x" -> countXboxGames();
                case "U/u" -> {
                    IO.println("Saliendo del sistema...");
                    running = false;
                }
                default -> IO.println("Opción inválida.\n");
            }
        }
    }


    // =======================================
    //      AGREGAR PRODUCTO
    // =======================================
    private static void addProduct() {

        // TIPO DE PRODUCTO
        IO.println("\n¿Qué tipo de producto deseas agregar?");
        byte productType = readByte("1.- Película", "2.- Videojuego", "Opción: ", new int[]{1, 2});

        // NOMBRE DEL PRODUCTO
        IO.println("============================");
        String productName = readString("Nombre del producto: ", "El nombre no puede estar vacío\n");

        // COSTO DE RENTA
        IO.println("============================");
        double productRentalCost = readProductRentalCost("Costo de renta: ");

        // DIAS DE RENTA
        IO.println("============================");
        int productRentedDays = readInt("Días de renta: ", "Días inválidos\n");

        // DISPONIBILIDAD
        IO.println("============================");
        byte availability = readByte("¿Está rentado?", "0.- Rentado", "1.- Disponible", new int[]{0, 1});

        // CREACIÓN DE INSTANCIA DEL PRODUCTO (MOVIE || VIDEOGAME)
        if (productType == 1) {
            IO.println("============================");
            String genre = readString("Género: ", "Campo inválido\n");

            IO.println("============================");
            int year = readInt("Año de producción: ", "Año inválido\n");

            movies.add(new Movie(productName, productRentalCost, productRentedDays, availability, genre, year));

        } else {

            IO.println("============================");
            String platform = readVideogamePlatform();

            IO.println("============================");
            String style = readString("Estilo: ", "Campo inválido\n");

            videogames.add(new Videogame(productName, productRentalCost, productRentedDays, availability, style, platform));
        }

        IO.println("\nProducto agregado exitosamente.");
    }

    // =======================================
    //      MOSTRAR PRODUCTOS
    // =======================================
    private static void showAllProducts() {
        // SE EVALUA LA EXISTENCIA DE PRODUCTOS EN AMBAS LISTAS
        if (videogames.isEmpty() && movies.isEmpty()) {
            IO.println("No hay productos registrados.\n");
            return;
        }
        IO.println("\n--- TODOS LOS PRODUCTOS ---");
        for (Movie movie : movies) IO.println(movie.toString());
        for (Videogame videogame : videogames) IO.println(videogame.toString());
    }

    private static void showAllMovies() {
        if (movies.isEmpty()) {
            IO.println("No hay peliculas registradas.\n");
            return;
        }

        IO.println("\n--- PELÍCULAS ---");
        for (Movie movie : movies) IO.println(movie.toString());
    }

    private static void showAllGames() {
        if (videogames.isEmpty()) {
            IO.println("No hay videojuegos registrados.\n");
            return;
        }
        IO.println("\n--- VIDEOJUEGOS ---");
        for (Videogame videogame : videogames) IO.println(videogame.toString());
    }

    // =======================================
    //      MÉTODOS DE CONSULTA
    // =======================================

    private static void checkMovieRented() {
        IO.print("Nombre de la película: ");
        String name = IO.readln();

        // Se recorre la lista de productos
        for (Movie movie : movies) {
            // Se valida si el nombre de la pelicula actual del bucle coincide con el nombre ingresado
            if (movie.getProductName().equalsIgnoreCase(name)) {
                // Se imprime "Si" en caso de que el
                IO.println("Rentado: " + (movie.getAvailability() == 1 ? "Sí" : "No"));
                return;
            }
        }
        IO.println("La película no existe.");
    }

    private static void checkGameRented() {
        IO.print("Nombre del videojuego: ");
        String name = IO.readln();
        for (Videogame videogame : videogames) {
            if (videogame.getProductName().equalsIgnoreCase(name)) {
                IO.println("Rentado: " + (videogame.getAvailability() == 1 ? "Sí" : "No"));
                return;
            }
        }
        IO.println("El videojuego no existe.");
    }

    private static void countMoviesRented() {
        int count = 0;
        if (!movies.isEmpty()) {
            for (Movie movie : movies)
                if (movie.getAvailability() == 1) count++;

            if (count == 0) IO.println("No hay peliculas rentadas");

            IO.println("Películas rentadas: " + count);
        }else {
            IO.println("No hay peliculas registradas");
        }
    }

    private static void countXboxGames() {
        int count = 0;
        if (!videogames.isEmpty()) {
            for (Videogame videogame : videogames)
                if (videogame.getPlatform().equalsIgnoreCase("Xbox One")) count++;

            if (count == 0) IO.println("No hay videojuegos de Xbox One");

            IO.println("Videojuegos en Xbox One: " + count);
        } else {
            IO.println("No hay videojuegos registrados");
        }
    }

    // =======================================
    //       MÉTODOS DE LECTURA Y VALIDACIÓN
    // =======================================

    // ---------- LECTURA Y VALIDACIÓN DE CAMPOS STRING ----------
    /* Valida la existencia de cadena de caracteres, evitando la propagación de campos vacios */
    static String readString(String msg, String error) {
        String str = "";
        // Ciclo while se ejecuta hasta que la variable "str" tenga contenido almacenado
        while (str.isEmpty()) {
            IO.print(msg);
            str = IO.readln();
            // Si la variable sigue vacia, se muestra mensaje de error.
            if (str.isEmpty()) IO.println(error);
        }
        return str;
    }

    // ---------- LECTURA Y VALIDACIÓN DE ENTEROS ----------
    /* Valida el tipo de dato correcto y valor, el cual debe ser mayor a 0 */
    static int readInt(String msg, String error) {
        int num;
        while (true) {
            try {
                IO.print(msg);
                num = Integer.parseInt(IO.readln());
                // Si el número es valido, se retorna el número y se corta la ejecución del método
                if (num > 0) return num;
                IO.println(error);
            } catch (Exception e) {
                IO.println("Ingresa un número válido\n");
            }
        }
    }

    // ---------- LECTURA Y VALIDACIÓN DE COSTO DE RENTA ----------
    /* Valida el tipo de dato correcto y valor, el cual debe ser mayor a 0 */
    static double readProductRentalCost(String msg) {
        double rentalCost;
        while (true) {
            try {
                IO.print(msg);
                rentalCost = Double.parseDouble(IO.readln());
                if (rentalCost > 0) return rentalCost;
                IO.println("Monto inválido\n");
            } catch (Exception e) {
                IO.println("Ingresa un número válido\n");
            }
        }
    }

    // ---------- LECTURA Y VALIDACIÓN DE OPCIONES (BYTE) ----------
    /* Valida el tipo de dato correcto, asi como la selección de una de las opciones disponibles
     * almacenadas en el arreglo de opciones*/
    static byte readByte(String msg1, String msg2, String msg3, int[] options) {
        byte selectedOption;
        while (true) {
            try {
                IO.println(msg1);
                IO.println(msg2);
                IO.print(msg3);
                selectedOption = Byte.parseByte(IO.readln());
                if (selectedOption == options[0] || selectedOption == options[1]) return selectedOption;
                IO.println("Opción inválida.\n");
            } catch (Exception e) {
                IO.println("Ingresa un número válido\n");
            }
        }
    }

    // ---------- ASIGNACIÓN DE PLATAFORMA DE VIDEOJUEGO ----------
    /* Con el objetivo de evitar errores de escritura, se controla el número de opciones, asimismo de
     * permitir solo la elección del tipo de plataforma, ya que otros métodos ocuparán la información ingresada
     * y podria ocasionar fallos en el programa */
    static String readVideogamePlatform() {
        byte option;
        while (true) {
            // LISTADO DE OPCIONES
            IO.println("Plataforma:");
            IO.println("1.- Xbox One");
            IO.println("2.- PS4");
            IO.println("3.- Nintendo Switch");

            try {
                option = Byte.parseByte(IO.readln());
                // Asignación de la variable "option", según el caso seleccionado
                return switch (option) {
                    case 1 -> "Xbox One";
                    case 2 -> "PS4";
                    case 3 -> "Nintendo Switch";
                    default -> {
                        IO.println("Opción inválida\n");
                        yield "";
                    }
                };
            } catch (Exception e) {
                IO.println("Ingresa un número válido\n");
            }
        }
    }
}
