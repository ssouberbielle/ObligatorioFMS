import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        menu();
    }


    public static void menu() throws IOException {
        boolean run = true;
        while (run) {
            System.out.println("Seleccione la opción que desee");
            System.out.println("1. Carga de datos");
            System.out.println("2. Ejecutar consultas");
            System.out.println("3. Salir");
            Scanner scanner = new Scanner(System.in);
            int respuesta = scanner.nextInt();
            switch (respuesta) {
                case 1:
                    long start = System.currentTimeMillis();
                    Book[] books = LibraryImpl.loadBooks();
                    LibraryImpl.loadUsers();
                    long finish = System.currentTimeMillis();
                    System.out.println("Carga de datos realizada, tiempo de ejecución de la carga: " + (finish-start));
                    break;
                case 2:
                    System.out.println("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.");
                    System.out.println("2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.");
                    System.out.println("3. Indicar el Top 10 de usuarios que realizaron mayor cantidad." +
                            " de evaluaciones a libros y ordenarlo por rating promedio descendente.");
                    System.out.println("4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas.");
                    System.out.println("5. Indicar el Top 20 de autores que más publicaciones han hecho por año.");
                    System.out.println("6. Salir");
                    respuesta = scanner.nextInt();
                    switch (respuesta) {
                        case 1:
                            long start1 = System.currentTimeMillis();
                            LibraryImpl.topTenReservation();
                            long finish1 = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (finish1-start1));
                            break;
                        case 2:
                            long start2 = System.currentTimeMillis();
                            LibraryImpl.topTwentyRating();
                            long finish2 = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (finish2-start2));
                            break;
                        case 3:
                            long start3 = System.currentTimeMillis();
                            LibraryImpl.topTenReviews();
                            long finish3 = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (finish3-start3));
                            break;
                        case 4:
                            long start4 = System.currentTimeMillis();
                            LibraryImpl.topFiveLanguages();
                            long finish4 = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (finish4-start4));
                            break;
                        case 5:
                            long start5 = System.currentTimeMillis();
                            LibraryImpl.topTwentyPublication();
                            long finish5 = System.currentTimeMillis();
                            System.out.println("Tiempo de ejecución: " + (finish5-start5));
                            break;
                        case 6:
                            run = false;
                    }
                    break;
                case 3:
                    run = false;

            }
        }
    }
}