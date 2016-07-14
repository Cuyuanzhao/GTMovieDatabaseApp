import java.sql.SQLException;

/**
 * Created by cuyuan on 7/9/16.
 */
public class SystemInfo {
    private static String mpassword;
    private static double cancelFee;
    private static double seniorDiscount;
    private static double childDiscount;

    public static String getMpassword() {
        return mpassword;
    }

    public static void setMpassword(String mpassword) {
        SystemInfo.mpassword = mpassword;
    }

    public static double getCancelFee() {
        return cancelFee;
    }

    public static void setCancelFee(double cancelFee) {
        SystemInfo.cancelFee = cancelFee;
    }

    public static double getSeniorDiscount() {
        return seniorDiscount;
    }

    public static void setSeniorDiscount(double seniorDiscount) {
        SystemInfo.seniorDiscount = seniorDiscount;
    }

    public static double getChildDiscount() {
        return childDiscount;
    }

    public static void setChildDiscount(double childDiscount) {
        SystemInfo.childDiscount = childDiscount;
    }
}
