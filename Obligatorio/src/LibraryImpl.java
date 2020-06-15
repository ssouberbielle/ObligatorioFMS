import LinkedList.LinkedList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LibraryImpl implements Library {


    public static Book[] loadBooks() throws IOException, FileNotFoundException {
        int i = 0;
        Book libros[] = new Book[10001];
        Reader in = new FileReader("fuentedatos/books.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        for (CSVRecord record : records) {
            String book_id = record.get(0);
            String isbn = record.get(1);
            String authors = record.get(2);
            String original_publication_year = record.get(3);
            String original_title = record.get(4);
            String title = record.get(5);
            String language = record.get(6);
            String image_url = record.get(7);
            //check carga de datos

            int year;
            try {
                year = Integer.parseInt(original_publication_year);
            } catch (NumberFormatException e) {
                year = 0;
            }
            long idBook;
            try {
                idBook = Integer.parseInt(book_id);
            } catch (NumberFormatException e) {
                idBook = 0;
            }

            Book book = new Book(idBook, isbn, authors, year, original_title, title, language, image_url);
            libros[i] = book;
            i++;
        }
        return libros;

    }

    public void addRating(long user_id, long book_id, int rating) {


    }

    public void makeReservation(long user_id, long book_id) {

    }

    public void topTenReservation() {

    }

    public void topTwentyReservation() {

    }

    public void topTenReviews() {

    }

    public void topTenLanguage() {

    }

    public void topTwentyPublication() {
    }

    public static LinkedList<User> loadUser() throws IOException {
        LinkedList<User> users = new LinkedList<User>();
        Reader in = new FileReader("fuentedatos/to_read.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        for (CSVRecord record : records) {
            String user_id = record.get(0);
            long idUser;
            try {
                idUser = Long.parseLong(user_id);
            } catch (NumberFormatException e) {
                idUser = 0;
            }
            User user = new User(idUser);
            if (users.contains(user)) {
                continue;
                 // se estan haciendo usuarios repetidos
            }
            users.add(user);

        }
        return users;

    }
}
