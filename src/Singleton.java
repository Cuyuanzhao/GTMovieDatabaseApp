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
    private static ChooseTheaterGui chooseTheaterGui = null;
    private static ChooseTheaterResult chooseTheaterResult = null;
    private static SelectTime selectTime = null;
    private static BuyTicketGui buyTicketGui = null;
    private static BuyTicketPayGui buyTicketPayGui = null;
    private static BuyTicketConfirmGui buyTicketConfirmGui = null;
    private static MyPaymentInfoGui myPaymentInfoGui = null;
    private static MyPreferredTheaterGui myPreferredTheaterGui = null;
    private static ManagerGui managerGui = null;
    private static RevenueReportGui revenueReportGui = null;
    private static PopularMovieGui popularMovieGui = null;

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

    public static ChooseTheaterGui getChooseTheaterGui() {
        if (chooseTheaterGui == null) {
            chooseTheaterGui = new ChooseTheaterGui();
        }
        return chooseTheaterGui;
    }

    public static ChooseTheaterResult getChooseTheaterResult() {
        if (chooseTheaterResult == null) {
            chooseTheaterResult = new ChooseTheaterResult();
        }
        return chooseTheaterResult;
    }

    public static SelectTime getSelectTime() {
        if (selectTime == null) {
            selectTime = new SelectTime();
        }
        return selectTime;
    }

    public static BuyTicketGui getBuyTicketGui() {
        if (buyTicketGui == null) {
            buyTicketGui = new BuyTicketGui();
        }
        return buyTicketGui;
    }

    public static BuyTicketPayGui getBuyTicketPayGui() {
        if (buyTicketPayGui == null) {
            buyTicketPayGui = new BuyTicketPayGui();
        }
        return buyTicketPayGui;
    }

    public static BuyTicketConfirmGui getBuyTicketConfirmGui() {
        if (buyTicketConfirmGui == null) {
            buyTicketConfirmGui = new BuyTicketConfirmGui();
        }
        return buyTicketConfirmGui;
    }

    public static MyPaymentInfoGui getMyPaymentInfoGui() {
        if (myPaymentInfoGui == null) {
            myPaymentInfoGui = new MyPaymentInfoGui();
        }
        return myPaymentInfoGui;
    }

    public static MyPreferredTheaterGui getMyPreferredTheaterGui() {
        if (myPreferredTheaterGui == null) {
            myPreferredTheaterGui = new MyPreferredTheaterGui();
        }
        return myPreferredTheaterGui;
    }

    public static ManagerGui getManagerGui() {
        if (managerGui == null) {
            managerGui = new ManagerGui();
        }
        return managerGui;
    }

    public static RevenueReportGui getRevenueReportGui() {
        if (revenueReportGui == null) {
            revenueReportGui = new RevenueReportGui();
        }
        return revenueReportGui;
    }

    public static PopularMovieGui getPopularMovieGui() {
        if (popularMovieGui == null) {
            popularMovieGui = new PopularMovieGui();
        }
        return popularMovieGui;
    }
}
