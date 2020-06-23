import LinkedList.LinkedList;
import Sort.BubbleSort;
import Sort.Merge;
import Sort.Sorting;
import hash.ClosedHashImpl;
import nodo.Wrapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import nodo.Node;
import javax.imageio.stream.ImageInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class LibraryImpl implements Library {

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

            String[] str = new String[2];
            str =language.split("-", 2);



            int year;
            try {
                year = Integer.parseInt(original_publication_year);
            } catch (NumberFormatException e) {
                year = 0;
            }
            long idBook = Long.parseLong(book_id);

            Author author = new Author(authors);
            Book book = new Book(idBook, isbn, author, year, original_title, title, str[0], image_url);
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
            user.getRatings().addFirst(rating1);

        }
        System.out.println("tan los usuarios ratings");
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
        for (int i = count.length - 1; i > count.length - 11; i--) {
            int amount = index[i];
            for (int a = 0; a < count.length; a++) {
                if (count[a] == amount) {
                    String title = books[a].getTitle();
                    System.out.println("Id del libro: " + a);
                    System.out.println("Titulo: " + title);
                    System.out.println("Cantidad de reservas: " + amount);
                    System.out.println("\n");
                }
            }

        }

    }

    public static void topTwentyRating() {
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
        for (int i = count.length - 1; i > count.length - 21; i--) {
            int amount = index[i];
            for (int a = 0; a < count.length; a++) {
                if (count[a] == amount) {
                    String title = books[a].getTitle();
                    System.out.println("Id del libro: " + a);
                    System.out.println("Titulo: " + title);
                    System.out.println("Cantidad de ratings: " + amount);
                    System.out.println("\n");
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

            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);

        }
        BubbleSort.bubbleSort(index);
        for (User usr : index) {
            System.out.println("ID del usuario: " + usr.getUser_id());
            System.out.println("Cantidad: " + usr.getRatings().getSize());
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
            System.out.println("Rating promedio: " + (df.format(usr.getRatingAvg())));
            System.out.println("\n");
        }




    }


    public static void topFiveLanguages() { // FIXME Podemos usar reserveNum en la consulta 1

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
            /*
/*





            /* Primero vamos a crear una lista con todos los idiomas que existen y un numero de reservas (ambos en un objeto)
               Despues, iteramos por los libros, y comparamos su idioma con los de la lista
               2 opcoines:
                    Iterar por los libros y comparar cada libro con un unico idioma (itero por idioma)
                    Iterar por los libros y comparar cada libro con todos los idiomas hasta que encuentres el correspondiente

               for libros
                    for idioma in listadeidiomas
                    if idioma del libro == idioma de lista:
                    idioma.reserva = idioma.reserva + libro.rserveNum

                    Pasamos los objetos esos de idiomas y reservas en un vector
                    ordenamos ese vector descendente
                    Tomamos los 5 valores

             */

        int[] count = new int[10000];
        for (User user : users) {
            LinkedList<Book> list = user.getReserves();
            for (Book book : list) {
                count[(int) book.getBook_id() - 1]++;
            }
        }
        Book[] vector = new Book[10000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = books[i];
            vector[i].setReserveNum(count[i]);
            Sorting.mergeSort(books, books.length);
        }
        for (Book book : books) {
            System.out.println(book.getReserveNum());
        }


        //Todo eso fue solo para tener la cantidad de reservas de cada libro y guardarlo en el atributo

        ClosedHashImpl<String, Integer> languages = new ClosedHashImpl<>(40);
        for (Book u : books) {
            if (!languages.contains(u.getLanguage())) {
                languages.put(u.getLanguage(), 0);

            }
        }
        int size = languages.filledBuckets();
        // Todo hasta aca es para saber si existe algun valor particular
        LinkedList<Wrapper<String>> language = new LinkedList<Wrapper<String>>();
        Node(Wrapper<String)
        language.addFirst() = new Wrapper<String>("espanol");
        language[0].setValue(3);
        System.out.println(language[0].getValue());


           /* int counter = 0;
            int reservas = 0;
            for (int c = 0; c < books.length; c++) {
                for (int b = 0; b < books.length; b++) {
                    for (String l : languages) {
                        if (books[b].getLanguage().equals(l)){
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        if (books[b].getLanguage().equals(books[b].getLanguage())) {
                            reservas = reservas + books[b].getReserveNum();


                        }



                    }


                }


            }

        }


*/
    }
    @Override
    public void topTwentyPublication() {
    }


}