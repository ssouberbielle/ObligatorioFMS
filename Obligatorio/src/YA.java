public class YA {
    private int year;
    private Author author;

    public YA(Author author, int year) {
        this.year = year;
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override // los objetos que son una key de un hash deben implementar un hashCode
    public int hashCode() {
        return author.getName().length() + Math.abs(year);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof YA)
            return this.year == ((YA) obj).year && this.author.getName().equals(((YA) obj).author.getName());
        return false;
    }
}
