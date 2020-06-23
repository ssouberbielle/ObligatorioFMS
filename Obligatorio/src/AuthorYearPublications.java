public class AuthorYearPublications implements Comparable <AuthorYearPublications> {
    private int publications;
    private Author author;
    private int year;


    public AuthorYearPublications(Author author, int year) {
        this.author = author;
        this.year = year;
        this.publications = 1;
    }

    public int getPublications() {
        return publications;
    }

    public void setPublications(int publications) {
        this.publications = publications;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    @Override
    public int compareTo(AuthorYearPublications o) {
        return Integer.compare(this.publications, o.publications);
    }
}

