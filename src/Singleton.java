import javax.naming.ldap.Rdn;

/**
 * Created by cuyuan on 7/8/16.
 */
public class Singleton {
    private static LogIn logIn = null;
    private static Register register = null;
    private static NowPlaying nowPlaying = null;
    private static MyInfo myInfo = null;
    private static OrderHistory orderHistory = null;
    private static OrderDetail orderDetail = null;
    private static MovieGui movieGui = null;
    private static OverviewGui overviewGui = null;
    private static ReviewGui reviewGui = null;
    private static GiveReviewGui giveReviewGui = null;

    public static LogIn getLogIn() {
        if (logIn == null) {
            logIn = new LogIn();
        }
        return logIn;
    }

    public static Register getRegister() {
        if (register == null) {
            register = new Register();
        }
        return register;
    }

    public static NowPlaying getNowPlaying() {
        if (nowPlaying == null) {
            nowPlaying = new NowPlaying();
        }
        return nowPlaying;
    }

    public static MyInfo getMyInfo() {
        if (myInfo == null) {
            myInfo = new MyInfo();
        }
        return myInfo;
    }

    public static OrderHistory getOrderHistory() {
        if (orderHistory == null) {
            orderHistory = new OrderHistory();
        }
        return orderHistory;
    }

    public static OrderDetail getOrderDetail() {
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
        }
        return orderDetail;
    }

    public static MovieGui getMovieGui() {
        if (movieGui == null) {
            movieGui = new MovieGui();
        }
        return movieGui;
    }

    public static OverviewGui getOverviewGui() {
        if (overviewGui == null) {
            overviewGui = new OverviewGui();
        }
        return overviewGui;
    }

    public static ReviewGui getReviewGui() {
        if (reviewGui == null) {
            reviewGui = new ReviewGui();
        }
        return reviewGui;
    }

    public static GiveReviewGui getGiveReviewGui() {
        if (giveReviewGui == null) {
            giveReviewGui = new GiveReviewGui();
        }
        return giveReviewGui;
    }
}
