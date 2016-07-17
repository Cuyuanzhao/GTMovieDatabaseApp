import java.sql.Date;
import java.sql.Time;

/**
 * Created by cuyuan on 7/9/16.
 */
public class Orders {
    private int orderId;
    private String username;
    private String movieTitle;
    private String theaterId;
    private String cardNumber;
    private int adultTix;
    private int seniorTix;
    private int childTix;
    private double totalCost;
    private Date date;
    private Time time;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    /*public enum Status{
        unused, cancelled, completed
    }
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }*/

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public int getAdultTix() {
        return adultTix;
    }

    public void setAdultTix(int adultTix) {
        this.adultTix = adultTix;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSeniorTix() {
        return seniorTix;
    }

    public void setSeniorTix(int seniorTix) {
        this.seniorTix = seniorTix;
    }

    public int getChildTix() {
        return childTix;
    }

    public void setChildTix(int childTix) {
        this.childTix = childTix;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
