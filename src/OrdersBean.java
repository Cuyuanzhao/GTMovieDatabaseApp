import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/9/16.
 */
public class OrdersBean {
/*    private String orderId = null;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }*/

    /**
     * return an order according to orderId;
     * @param orderId
     * @return
     */
    public Orders getFromOrderId(int orderId) {
        Orders result = null;
        try {
            String sql = "SELECT * FROM ORDERS WHERE Order_id = " + orderId;
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                result = getFromRow(Global.jrs);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    /**
     * return the order array according to username;
     * @param Global.getUsername;
     * @return
     */
    public ArrayList<Orders> getFromUsername(String username) {
        ArrayList<Orders> result = new ArrayList<Orders>();
        try {
            String sql = "SELECT * FROM ORDERS WHERE Username = '" + username + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            while (Global.jrs.next()) {
                result.add(getFromRow(Global.jrs));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    private Orders getFromRow(JdbcRowSetImpl jrs) {
        Orders result = new Orders();
        try {
            result.setOrderId(jrs.getInt("Order_id"));
            result.setUsername(jrs.getString("Username"));
            result.setMovieTitle(jrs.getString("Mtitle"));
            result.setTheaterId(jrs.getString("Theater_id"));
            result.setCardNumber(jrs.getString("Card_number"));
            result.setAdultTix(jrs.getInt("AdultTix"));
            result.setSeniorTix(jrs.getInt("SeniorTix"));
            result.setChildTix(jrs.getInt("ChildTix"));
            result.setTotalCost(jrs.getDouble("Total_cost"));
            result.setDate(jrs.getDate("Date"));
            result.setTime(jrs.getTime("Time"));
            result.setStatus(jrs.getString("Status"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

}
