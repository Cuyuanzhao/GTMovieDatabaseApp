import java.security.Timestamp;

/**
 * Created by cuyuan on 7/12/16.
 */
public class PlaysAt {
    private String movieTitle;
    private String theaterId;
    private double price;
    private Timestamp showTime;
    private boolean playing;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getShowTime() {
        return showTime;
    }

    public void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
