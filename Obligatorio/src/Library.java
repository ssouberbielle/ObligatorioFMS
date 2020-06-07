public interface Library {

    void createBook(long book_id, String isbn, int publication_year, String originalTitle,
                    String title, String language, String image_url);

    void addRating(long user_id, long book_id, int rating);

    void makeReservation(long user_id, long book_id); // user_id resrvas++

    void topTenReservation();

    void topTwentyReservation();

    void topTenReviews();

    void topTenLanguage();

    void topTwentyPublication();






}
