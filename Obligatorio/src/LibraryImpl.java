import LinkedList.LinkedList;
import Sort.Merge;
import hash.ClosedHashImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class LibraryImpl implements Library {

    private static Book[] books = new Book[10000];
    private static ClosedHashImpl<Long, User> users = new ClosedHashImpl<Long, User>(54000);

    public static Book[] loadBooks() throws IOException {
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
            str = language.split("-", 2);

            String[] authorArray = new String[6];
            authorArray = authors.split(", ");

            int year;
            try {
                year = Integer.parseInt(original_publication_year);
            } catch (NumberFormatException e) {
                year = 0;
            }
            long idBook = Long.parseLong(book_id);

            LinkedList<Author> authorList = new LinkedList<Author>();
            for (String a : authorArray) {
                if (a != null) {
                    Author author = new Author(a);
                    authorList.addFirst(author);

                }
            }

            if (str[0].equals("en")) str[0] = "eng";
            Book book = new Book(idBook, isbn, authorList, year, original_title, title, str[0], image_url);
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
        return users;
    }


    public static void topTenReservation() {
        int[] count = new int[10000];
        for (User usr : users) {
            LinkedList<Book> list = usr.getReserves(); // lista de reservas de usuario actual
            for (Book book : list) {
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
            for (Rating rating : list) {
                count[(int) (rating.getBook_id() - 1)]++; // suma 1 a la posicio n correspondiente
                                                          // a la del libro en el vector de libros
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
            personas[num++] = persona;// llenar vector personas con los usuarios
        }
        Sorting.mergeSort(personas, personas.length); // ordenamos por cantidad de rating
        for (int i = personas.length - 1; i > personas.length - 11; i--) {
            index[a - 1] = personas[i];
            a++; // ponemos los ultimos 10 en index
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
        Sorting.bubbleSort(index);
        for (User usr : index) {
            System.out.println("ID del usuario: " + usr.getUser_id());
            System.out.println("Cantidad: " + usr.getRatings().getSize());
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
            System.out.println("Rating promedio: " + (df.format(usr.getRatingAvg())));
            System.out.println("\n");
        }
    }

    public static void topFiveLanguages() {

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

        }
        LanguageTimes.Consulta4(books, users);

    }

    public static void topTwentyPublication() {
        boolean trece = false;
        ClosedHashImpl<YA, AuthorYearPublications> publishPerYear = new ClosedHashImpl<>(20000);
        for (Book book : books) {
            for (Author author : book.getAuthors()) {
                int year = book.getOriginal_publication_year();
                YA ya = new YA(author, year);
                AuthorYearPublications ayp = publishPerYear.get(ya);
                if (ayp == null) {
                    ayp = new AuthorYearPublications(author, year);
                    publishPerYear.put(ya, ayp);

                } else {
                    ayp.setPublications(ayp.getPublications() + 1);
                }
            }
        }

        int num = 0;
        AuthorYearPublications[] twenty = new AuthorYearPublications[publishPerYear.getSize()];

        for (AuthorYearPublications ayp : publishPerYear) {
            twenty[num++] = ayp; // llenar vector twenty con los ayp
        }

        Sorting.mergeSort(twenty, twenty.length); // ordenamos por cantidad de publicaciones por ano por autor

        for (int i = twenty.length - 1; i > twenty.length - 21; i--) {
            System.out.println("Autor: "+ twenty[i].getAuthor().getName());
            System.out.println("Año de publicacion: " + twenty[i].getYear());
            System.out.println("Cantidad: " + twenty[i].getPublications());
        }
    }
}


