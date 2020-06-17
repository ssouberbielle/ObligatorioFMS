import LinkedList.LinkedList;
import hash.OpenHash;
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
                idBook = Long.parseLong(book_id);
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

    public static OpenHash<Long,User> loadUsers() throws IOException {
        boolean primero = true;
        OpenHash<Long,User> users = new OpenHash<Long,User>(10000);
        Reader in = new FileReader("fuentedatos/to_read.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
        for (CSVRecord record : records) {
            String user_id = record.get(0);
            String book_id = record.get(1);

            long idUser;
            try {
                idUser = Long.parseLong(user_id);
            } catch (NumberFormatException e) {
                idUser = 0;
            }
            long idBook;
            try {
                idBook = Long.parseLong(book_id);
            } catch (NumberFormatException e) {
                idBook = 0;
            }
            if(primero == false) {
                User user = new User(idUser);

                if (users.contains(idUser)) { // PROBAMOS
                    user = users.get(idUser);
                } else {

                    users.put(idUser, user);
                }
                user.getReserves().add(idBook);

            }
         primero = false;

        }

        primero = true;
        Reader in2 = new FileReader("fuentedatos/ratings.csv");
        Iterable<CSVRecord> records2 = CSVFormat.RFC4180.parse(in2);
        for (CSVRecord record : records2) {
            String user_id2 = record.get(0);
            String book_id2 = record.get(1);
            String rating = record.get(2);

            long idUser2;
            try {
                idUser2 = Long.parseLong(user_id2);
            } catch (NumberFormatException e) {
                idUser2 = 0;
            }
            long idBook2;
            try {
                idBook2 = Long.parseLong(book_id2);
            } catch (NumberFormatException e) {
                idBook2 = 0;
            }

            int rati;
            try{
                rati = Integer.parseInt(rating);
            } catch (NumberFormatException e) {
                rati = 0;
            }

            if(primero == false){
                Rating rating1 = new Rating(rati,idBook2);
                User temp = new User(idUser2);

                if (users.contains(idUser2)) {
                    temp = users.get(idUser2);
                } else {
                    users.put(idUser2, temp);
                }
                temp.getRatings().add(rating1);



            }

            primero = false;


        }
        return users;
    }


}
