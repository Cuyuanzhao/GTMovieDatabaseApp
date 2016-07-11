/**
 * Created by cuyuan on 7/9/16.
 */
public class SystemInfo {
    private String mpassword;
    private double cancelFee;
    private double seniorDiscount;
    private double childDiscount;

    public String getMpassword() {
        return mpassword;
    }
    public double getCancelFee() {
        return cancelFee;
    }
    public double getSeniorDiscount() {
        return seniorDiscount;
    }
    public double getChildDiscount() {
        return childDiscount;
    }
    public void setMpassword(String m) {
        mpassword = m;
    }
    public void setCancelFee(double c) {
        cancelFee = c;
    }
    public void setSeniorDiscount(double s) {
        seniorDiscount = s;
    }
    public void setChildDiscount(double c) {
        childDiscount = c;
    }
}
