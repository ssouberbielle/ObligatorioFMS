public class Book {
    private long bookid;
    private String isbn;
    private Author authors;
    private int original_publication_year;
    private String original_title;
    private String title;
    private String language;
    private String image_url;




    public Book(long bookid, String isbn, Author authors, int original_publication_year, String original_title,
                String title, String language, String image_url) {
        this.bookid = bookid;
        this.isbn = isbn;
        this.authors = authors;
        this.original_publication_year = original_publication_year;
        this.original_title = original_title;
        this.title = title;
        this.language = language;
        this.image_url = image_url;
    }


    public long getBook_id() {
        return bookid;
    }

    public void setBook_id(long bookid) {
        this.bookid = bookid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthors() {
        return authors;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    public int getOriginal_publication_year() {
        return original_publication_year;
    }

    public void setOriginal_publication_year(int original_publication_year) {
        this.original_publication_year = original_publication_year;
    }
    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
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

}
