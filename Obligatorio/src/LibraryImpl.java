public class LibraryImpl implements Library {

    public static void addBook(String bookid, String isbn, String authors, String original_publication_year,String original_title, String title, String language, String image_url) {
        Book book = new Book(bookid, isbn, authors, original_publication_year, original_title, title, language, image_url);
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
}
