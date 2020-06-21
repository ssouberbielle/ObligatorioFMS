import LinkedList.LinkedList;
import nodo.Wrapper;


public class User implements Comparable<User> {
    private long user_id;
    private LinkedList<Book> reserves = new LinkedList<Book>(); //tuve que cambiar el tipo a Book
    private LinkedList<Rating> ratings = new LinkedList<Rating>();


    public User(long user_id) {
        this.user_id = user_id;
    }



    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }


    public LinkedList<Book> getReserves() {
        return reserves;
    }

    public void setReserves(LinkedList<Book> reserves) {
        this.reserves = reserves;
    }

    public LinkedList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(LinkedList<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public int compareTo(User o) {
        return this.getRatings().getSize() - o.getRatings().getSize();
    }
}

