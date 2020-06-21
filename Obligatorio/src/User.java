import LinkedList.LinkedList;
import nodo.Wrapper;


public class User implements Comparable<User> {
    private long user_id;
    private LinkedList<Book> reserves = new LinkedList<Book>(); //tuve que cambiar el tipo a Book
    private LinkedList<Rating> ratings = new LinkedList<Rating>();
    private int ratingNum = this.getRatings().getSize();

    public User(long user_id) {
        this.user_id = user_id;
    }

    public int getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
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
    public int compareTo(User o) {return 0;}
       /* int toReturn = 0;
        if (this.ReserveNum > o.value) {
            toReturn = 1;
            return toReturn;
        }
        if (this.value < o.value) {
            toReturn = -1;
            return toReturn;
        }
        return toReturn;
    }*/
    }

