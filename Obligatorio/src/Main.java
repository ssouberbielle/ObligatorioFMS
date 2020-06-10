import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
                   /* Reader in = new FileReader("/fuentedatos/books.csv");
                    Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
                    for (CSVRecord record : records) {
                        String book_id = record.get("Last Name");
                        String firstName = record.get("First Name");
                    } */

                    Reader in = new FileReader("books.csv");
                    Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
                    for (CSVRecord record : records) {
                        String bookid = record.get(0);
                        String isbn = record.get(1);
                        String authors = record.get(2);
                        String original_publication_year = record.get(3);
                        String original_title = record.get(4);
                        String title = record.get(5);
                        String language = record.get(6);
                        String image_url = record.get(7);
                    }

                   /* final Appendable out =
                    final CSVPrinter printer = CSVFormat.DEFAULT.withHeader("H1", "H2").print(out); */









                    menu();
                    break;
                case 2:
                    System.out.println("1. Indicar el Top 10 de libros que más lecturas tienen por parte de usuarios.");
                    System.out.println("2. Indicar el Top 20 de libros que más cantidad de lecturas tienen.");
                    System.out.println("3. Indicar el Top 10 de usuarios que realizaron mayor cantidad" +
                            " de evaluaciones a libros y ordenarlo por rating promedio descendente");
                    System.out.println("4. Indicar el Top 5 de los idiomas asociados a libros que han tenido más reservas");
                    System.out.println("5. Indicar el Top 20 de autores que más publicaciones han hecho por año");
                    System.out.println("6. Salir");
                    respuesta = scanner.nextInt();
                    switch (respuesta) {
                        case 1: //consulta 1
                            break;
                        case 2: //consulta 1
                            break;
                        case 3: //consulta 1
                            break;
                        case 4: //consulta 1
                            break;
                        case 5: //consulta 1
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
