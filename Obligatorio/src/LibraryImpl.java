import LinkedList.LinkedList;
import Sort.Merge;
import hash.ClosedHashImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import LinkedList.MyIteratorLinkedList;
import nodo.Node;
import sun.invoke.util.Wrapper;


public class LibraryImpl implements Library {
    private static Book[] books = new Book[10000];
    private static ClosedHashImpl<Long,User> users = new ClosedHashImpl<Long,User>(40000);

    public static Book[] loadBooks() throws IOException, FileNotFoundException {
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

            int year;
            try {
                year = Integer.parseInt(original_publication_year);
            } catch (NumberFormatException e) {
                year = 0;
            }
            long idBook = Long.parseLong(book_id);

            Author author = new Author(authors);
            Book book = new Book(idBook, isbn, author, year, original_title, title, language, image_url);
            books[i] = book;
            i++;
        }
        return books;

    }
    public static ClosedHashImpl<Long, User> loadUsers() throws IOException {
        Reader in = new FileReader("fuentedatos/to_read.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String user_id = record.get(0);
            String book_id = record.get(1);

            long idUser = Long.parseLong(user_id);
            long idBook = Long.parseLong(book_id);

            User user = users.get(idUser);
            if (user == null) {
                user = new User(idUser);
                users.put(idUser, user);
            }
            Book book = books[(int) idBook - 1];
            if (book !=null) user.getReserves().addFirst(book); //me marca que tiene que mandarse un long y aca pasas un book

        }
        System.out.println("tan los usuarios reservas");


        Reader in2 = new FileReader("fuentedatos/ratings.csv");
        Iterable<CSVRecord> records2 = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in2);
        for (CSVRecord record : records2) {
            String user_id2 = record.get("user_id");
            String book_id2 = record.get("book_id");
            String rating = record.get("rating");


            long idUser2 = Long.parseLong(user_id2);
            long idBook2 = Long.parseLong(book_id2);
            int rati = Integer.parseInt(rating);

            Rating rating1 = new Rating(rati, idBook2);
            User user = users.get(idUser2);
            if (user == null) {
                user = new User(idUser2);
                users.put(idUser2, user);
            }
            Book book = books[(int) idBook2 - 1];
            if (book !=null) user.getReserves().add(book); //mismo error que antes, se deberia pasar un long
            user.getRatings().addFirst(rating1);

        }
        System.out.println("tan los usuarios");
        return users;
    }

    public void addRating(long user_id, long book_id, int rating) {


    }

    public void makeReservation(long user_id, long book_id) {

    }

    public static void topTenReservation() {
        int [] count = new int [10000];
        for (int usr = 1; usr < users.getSize() + 1; usr++) { // forma rustica de buscar las keys, no se si hay agujeros
            LinkedList<Long> list = users.get((long) usr).getReserves(); //lista de reservas de usuario actual
            MyIteratorLinkedList<Book> iterator = new MyIteratorLinkedList<Book>(list.getFirst()); //tira required type Book y provided long. Deberiamos guardar el libro entero en reservas

            while (iterator.hasNext()) {
                count[(int) (iterator.getNodo().getValue().getBook_id() - 1)]++; // suma 1 a la posicio
                // n correspondiente a la del libro en el vector de libros
            }
        }
        int []index = count.clone();
        Merge.mergeSort(index,index.length);
        for (int i = 0; i < 10; i++){
            int amount = index[index.length - i - 1];
            for (int a : count){
                if (count[a] == amount){
                    String title = books[a].getTitle();
                    System.out.println("Id del libro " + a);
                    System.out.println("Titulo " + title);
                    System.out.println("Cantidad " + amount);
                }
            }

        }
    }

    public void topTwentyReservation() {
        int [] count = new int [10000];
        for (int usr = 0; usr < users.getSize(); usr++) { // forma rustica de buscar las keys, no se si hay agujeros
            LinkedList<Rating> list = users.get((long) usr).getRatings(); //lista de reservas de usuario actual
            MyIteratorLinkedList<Rating> iterator = new MyIteratorLinkedList<Rating>(list.getFirst());

            while (iterator.hasNext()) {
                count[(int) (iterator.getNodo().getValue().getBook_id() - 1)]++; // suma 1 a la posicio
                // n correspondiente a la del libro en el vector de libros
            }
        }
        int []index = count.clone();
        Merge.mergeSort(index,index.length);
        for (int i = 0; i < 10; i++){
            int amount = index[index.length - i - 1];
            for (int a : count){
                if (count[a] == amount){
                    String title = books[a].getTitle();
                    System.out.println("Id del libro " + a);
                    System.out.println("Titulo " + title);
                    System.out.println("Cantidad " + amount);
                }
            }

        }
    }

    public void topTenReviews() {
        Wrapper<Long>[] array = (Wrapper<Long>[]) new Comparable[53424]; //el import de Wrapper no es el mismo, probablemente feli uso una version de java mas nueva
        long UserIteration = 1;
        // Wrapper<Long, Integer, Float>[] array = (Wrapper<Long, Integer, Float>[]) new Object[52424];
        while (users.get(UserIteration) != null) {
            int reviews = (users.get(UserIteration).getRatings().getSize()); // cantidad ratings
            LinkedList<Rating> list = users.get(UserIteration).getRatings();
            MyIteratorLinkedList<Rating> iterator = new MyIteratorLinkedList<Rating>(list.getFirst());
            int sum = 0;
            int counter = 1;
            while (iterator.hasNext()) {
                Long bookId = iterator.getNodo().getValue().getBook_id();
                sum = sum + iterator.getNodo().getValue().getRating();
                if (counter == reviews) {
                    Node<Long> usr_amount = new Node<Long>(bookId);
                    usr_amount.setPriority(reviews);
                }
            }
            UserIteration++;
        }
    }

    public void topTenLanguage() {

    }
    //  Wrapper<Long, Integer, Float> packet = new Wrapper<Long, Integer, Float>(bookId);
    //  packet.setT(reviews);
    //  packet.setV((float) (sum / reviews));

        /*No es necesario tener los 3 a la vez, alcanzaría con tener idUsuario y cantidad de reservas. Una vez
        que ordenes ese vector, entonces podés calcular los rating promedio de cada uno para ordenar decresciente
        No sé si es mejor conocer el promedio rating al principio o al final, supongo que al final.
        Sí, mejor al final porque se calculan solamente los rating promedio de los primeros 10, así que podemos
        iterar tranqui me parece, no sería tanto más largo
        */

    public void topFiveLanguages(){ // Acá creo que podemos usar Enum, se había hablado de usar, pero no sé
        // Igual me parece que hay que hacer tremendo switch sí o sí


    }

    public void topTwentyPublication() {
    }



}