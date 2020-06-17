public class Rating {
    private int rating;
    private long book_id;


    public Rating(int rating, long book_id) {
        this.rating = rating;
        this.book_id = book_id;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }
}
