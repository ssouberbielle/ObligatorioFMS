import LinkedList.LinkedList;
import nodo.Wrapper;


public class User implements Comparable<User> {
    private long user_id;
    private LinkedList<Book> reserves = new LinkedList<Book>(); //tuve que cambiar el tipo a Book
    protected LinkedList<Rating> ratings = new LinkedList<Rating>();
    private float ratingAvg;

    public User(long user_id) {
        this.user_id = user_id;
    }

    public void setRatingAvg(float ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public float getRatingAvg() {
        /*for(int i=0; i<getRatings().getSize(); i++) {
            ratingAvg = ((ratingAvg + getRatings().get(i).getRating())/getRatings().getSize());
        }*/
        return ratingAvg;
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

    public int compareToRating(User o) {
        return this.getRatings().getSize() - o.getRatings().getSize();
    }

    public float compareToAvg(User o){
        return this.getRatingAvg() - o.getRatingAvg();
    }


    @Override
    public int compareTo(User o){
        return this.ratings.getSize() - o.ratings.getSize();
    }
}

class UserCompByRatings extends User {
    public UserCompByRatings(long userId) {
        super(userId);
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}