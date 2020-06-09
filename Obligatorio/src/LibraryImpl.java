public class LibraryImpl implements Library {

    public void addBook(long book_id, String isbn, int publication_year, String originalTitle, String title, String language, String image_url) {
        Book book = new Book(book_id, isbn, publication_year, originalTitle, title, language, image_url);
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
