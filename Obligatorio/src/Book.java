public class Book {
    private long book_id;
    private String isbn;
    private int publication_year;
    private String originalTitle;
    private String title;
    private String language;
    private String image_url;
    private int cantidadReservas;

    public Book(long book_id, String isbn, int publication_year, String originalTitle,
                String title, String language, String image_url) {
        this.book_id = book_id;
        this.isbn = isbn;
        this.publication_year = publication_year;
        this.originalTitle = originalTitle;
        this.title = title;
        this.language = language;
        this.image_url = image_url;
    }


    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getCantidadReservas() {
        return cantidadReservas;
    }

    public void setCantidadReservas(int cantidadReservas) {
        this.cantidadReservas = cantidadReservas;
    }
}
