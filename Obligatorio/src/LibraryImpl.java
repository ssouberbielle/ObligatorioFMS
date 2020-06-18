import LinkedList.LinkedList;
import hash.ClosedHashImpl;
import hash.HashNode;
import hash.OpenHash;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import LinkedList.MyIteratorLinkedList;

public class LibraryImpl implements Library {

    public Book[] books = new Book [10000];
    ClosedHashImpl<Long, User> users = new ClosedHashImpl<Long, User>(28000);

    public Book[] loadBooks() throws IOException, FileNotFoundException {
        int i = 0;
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
            books[i] = book;
            i++;

            author.getBooks().add(book); // agregar libros a sus respectivos autores
        }
        return books;

    }


    public void addRating(long user_id, long book_id, int rating) {


    }

    public void makeReservation(long user_id, long book_id) {

    }

    @Override
    public void topTenReservation() {
        int[] cuenta = new int [10000];
        for (int usr = 0; usr < users.getSize(); usr++){ // forma rustica de buscar las keys, no se si hay agujeros
            LinkedList<Long> list = users.get((long) usr).getReserves();
            MyIteratorLinkedList<Long> iterator = new MyIteratorLinkedList<Long>(list.getFirst());
            while (iterator.hasNext()){
                cuenta[ (iterator.getNodo().getValue().intValue())-1]++;
                //cuenta.sort
                //sacar 10 de arriba


            }



        }

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

    public ClosedHashImpl<Long, User> loadUsers() throws IOException {
        Reader in = new FileReader("fuentedatos/to_read.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String user_id = record.get(0);
            String book_id = record.get(1);

            long idUser = Long.parseLong(user_id);
            long idBook = Long.parseLong(book_id);

            User user = new User(idUser);

            if (users.get(idUser) != null) { // PROBAMOS
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

            if (users.get(idUser2) != null) {
                temp = users.get(idUser2);
            }else {
                users.put(idUser2, temp);
            }
            temp.getRatings().add(rating1);


        }
        return users;
    }

}
