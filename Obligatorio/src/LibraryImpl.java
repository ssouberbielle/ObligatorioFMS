import LinkedList.LinkedList;
import hash.HashNode;
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
        Book libros[] = new Book[10000];
        Reader in = new FileReader("fuentedatos/books.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String book_id = record.get(0);
            String isbn = record.get(1);
            String authors = record.get(2);
            String original_publication_year = record.get(3);
            String original_title = record.get(4);
            String title = record.get(5);
            String language = record.get(6);
            String image_url = record.get(7);

            int year = Integer.parseInt(original_publication_year);
            long idBook = Long.parseLong(book_id);

            Author author = new Author(authors);
            Book book = new Book(idBook, isbn, author, year, original_title, title, language, image_url);
            libros[i] = book;
            i++;
        }
        return libros;

    }


    public void addRating(long user_id, long book_id, int rating) {


    }

    public void makeReservation(long user_id, long book_id) {

    }

    @Override
    public void topTenReservation() {


    }

    public void topTenReservation(Book[] libros, OpenHash<Long, User> users) {
      //  for (HashNode<Long, User> tempUser: users)

    }

    public void topTwentyReservation() {

    }

    public void topTenReviews() {

    }

    public void topTenLanguage() {

    }

    public void topTwentyPublication() {
    }

    public static OpenHash<Long, User> loadUsers() throws IOException {
        OpenHash<Long, User> users = new OpenHash<Long, User>(28000);
        Reader in = new FileReader("fuentedatos/to_read.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String user_id = record.get(0);
            String book_id = record.get(1);

            long idUser = Long.parseLong(user_id);
            long idBook = Long.parseLong(book_id);

            User user = new User(idUser);

            if (users.contains(idUser)) { // PROBAMOS
                user = users.get(idUser);


            } else {

                users.put(idUser, user);
            }
            user.getReserves().add(idBook);

        }

        Reader in2 = new FileReader("fuentedatos/ratings.csv");
        Iterable<CSVRecord> records2 = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in2);
        int n = 0;
        for (CSVRecord record : records2) {
            String user_id2 = record.get("user_id");
            String book_id2 = record.get("book_id");
            String rating = record.get("rating");


            long idUser2 = Long.parseLong(user_id2);
            long idBook2 = Long.parseLong(book_id2);
            int rati = Integer.parseInt(rating);

            Rating rating1 = new Rating(rati, idBook2);
            User temp = new User(idUser2);

            if (users.contains(idUser2)) {
                temp = users.get(idUser2);
            }else {
                users.put(idUser2, temp);
            }
            temp.getRatings().add(rating1);


        }
        return users;
    }

}
