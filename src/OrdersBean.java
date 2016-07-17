import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/9/16.
 */
public class OrdersBean {

    public int insert(Orders order) {
        int orderId = -1;
        try {
            String sql = "SELECT * FROM ORDERS";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            Global.jrs.moveToInsertRow();
            Global.jrs.updateString("Username", order.getUsername());
            Global.jrs.updateString("Mtitle", order.getMovieTitle());
            Global.jrs.updateString("Theater_id", order.getTheaterId());
            Global.jrs.updateString("Card_number", order.getCardNumber());
            Global.jrs.updateString("Status", order.getStatus());
            Global.jrs.updateDouble("Total_cost", order.getTotalCost());
            Global.jrs.updateInt("AdultTix", order.getAdultTix());
            Global.jrs.updateInt("SeniorTix", order.getSeniorTix());
            Global.jrs.updateInt("ChildTix", order.getChildTix());
            Global.jrs.updateDate("Date", order.getDate());
            Global.jrs.updateTime("Time", order.getTime());
            Global.jrs.insertRow();
            Global.jrs.moveToCurrentRow();
            String sql1 = "SELECT MAX(Order_id) FROM ORDERS";
            Global.jrs.setCommand(sql1);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                orderId = Global.jrs.getInt(1);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return orderId;
    }

    public boolean canGiveReview(String username, String movieTitle) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM ORDERS WHERE Username = '" + username + "' AND MTitle = '" + movieTitle + "' AND Status = 'completed'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                result = true;
            }
        } catch(SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    /**
     * cancel an order
     * @param orderId
     */
    public void cancelOrder(int orderId) {
        try {
            String sql = "SELECT * FROM ORDERS WHERE Order_id = " + orderId;
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                double totalCostAfterCancel = Global.jrs.getDouble("Total_cost") - SystemInfo.getCancelFee();
                Global.jrs.updateDouble("Total_cost", totalCostAfterCancel);
                Global.jrs.updateString("Status", "cancelled");
                Global.jrs.updateRow();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * return an order according to orderId;
     * @param orderId
     * @return
     */
    public Orders getFromOrderId(int orderId) {
        Orders result = null;
        try {
            String sql = "SELECT * FROM ORDERS WHERE Order_id = " + orderId;// + " AND Username = '" + Global.getUsername() + "'";
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
