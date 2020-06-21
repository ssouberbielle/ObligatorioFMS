public interface Library {

    static void loadBooks(String bookid, String isbn, String authors,
                          String original_publication_year,String original_title,
                          String title, String language, String image_url) {

    }

    void addRating(long user_id, long book_id, int rating);

    void makeReservation(long user_id, long book_id); // user_id resrvas++


    static void topTwentyReservation(){
    }

    void topTenReviews();

    void topTenLanguage();

    void topTwentyPublication();


}

