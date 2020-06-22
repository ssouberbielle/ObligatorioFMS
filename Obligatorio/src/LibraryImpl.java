import LinkedList.LinkedList;
import Sort.Merge;
import hash.ClosedHashImpl;
import heap.HeapMax;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import nodo.Node;
import nodo.Wrapper;


public class LibraryImpl<K> implements Library {

    private static Book[] books = new Book[10000];
    private static ClosedHashImpl<Long, User> users = new ClosedHashImpl<Long, User>(54000); // antes 40000 y no andaba

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
            if (book != null) user.getReserves().addFirst(book);

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
            if (book != null) user.getReserves().add(book);
            user.getRatings().addFirst(rating1);

        }
        System.out.println("tan los usuarios ratings");
        System.out.println(users.get(43l).getRatings().getSize());
        return users;
    }

    public void addRating(long user_id, long book_id, int rating) {


    }

    public void makeReservation(long user_id, long book_id) {

    }

    public static void topTenReservation() {

        int[] count = new int[10000];
        for (User usr : users) {
            LinkedList<Book> list = usr.getReserves(); //lista de reservas de usuario actual
            for (Book book : list) { //tira required type Book y provided long. Deberiamos guardar el libro entero en reservas

                count[(int) (book.getBook_id() - 1)]++; // suma 1 a la posicio
                // n correspondiente a la del libro en el vector de libros
            }
        }

            int[] index = count.clone();
            Merge.mergeSort(index, index.length);
            for (int i = count.length-1; i > count.length-11 ; i--) {
                int amount = index[i];
                for (int a = 0; a < count.length; a++) {
                    if (count[a] == amount) {
                        String title = books[a].getTitle();
                        System.out.println("Id del libro: " + a);
                        System.out.println("Titulo: " + title);
                        System.out.println("Cantidad de reservas: " + amount);
                    }
                }

            }

    }

        public static void topTwentyRating () {
            int[] count = new int[10000];
            for (User usr : users) {
                LinkedList<Rating> list = usr.getRatings(); //lista de reservas de usuario actual
                for (Rating rating : list) { //tira required type Book y provided long. Deberiamos guardar el libro entero en reservas

                    count[(int) (rating.getBook_id() - 1)]++; // suma 1 a la posicio
                    // n correspondiente a la del libro en el vector de libros
                }
            }

            int[] index = count.clone();
            Merge.mergeSort(index, index.length);
            for (int i = count.length-1; i > count.length-21 ; i--) {
                int amount = index[i];
                for (int a = 0; a < count.length; a++) {
                    if (count[a] == amount) {
                        String title = books[a].getTitle();
                        System.out.println("Id del libro: " + a);
                        System.out.println("Titulo: " + title);
                        System.out.println("Cantidad de ratings: " + amount);
                    }
                }

            }

        }

        public static void topTenReviews() {
            User[] personas = new User[users.getSize()];
            User[] index = new User[10];
            float promedio = 0;
            int a = 1;
            int num = 0;
            for (User persona : users) {
                personas[num++] = persona;
            }
            Sorting.mergeSort(personas, personas.length);
            for (int i = personas.length - 1; i > personas.length - 11; i--) {
                index[a - 1] = personas[i];
                //System.out.println(index[a - 1].getRatings().getSize()); // verifico que el sort funciona
                a++;
            }

            int suma = 0;
            for (int b = 0; b < 10; b++) {
                for (int j = 0; j < index[b].getRatings().getSize(); j++) {
                    suma = (suma + index[b].getRatings().get(j).getRating());
                }
                promedio = ((float) suma / index[b].getRatings().getSize());
                index[b].setRatingAvg(promedio);
                suma = 0;
                System.out.println(index[b].getRatingAvg()); //IMPRIMO LOS PROMEDIOS AHORA SOLO FALTA ORENARLOS
            }


        }


        public void topTenLanguage(){

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

        public void topFiveLanguages () { // Acá creo que podemos usar Enum, se había hablado de usar, pero no sé
            // Igual me parece que hay que hacer tremendo switch sí o sí


        }

        public void topTwentyPublication () {
        }



}