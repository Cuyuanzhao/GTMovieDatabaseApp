import com.sun.rowset.JdbcRowSetImpl;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/16/16.
 */
public class PaymentInfoBean {

    public void unSave(String cardNumber, String username) {
        try {
            String sql = "SELECT * FROM PAYMENT_INFO WHERE Saved = 1 AND Username = '" + username + "' AND Card_number = '" + cardNumber + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                Global.jrs.updateBoolean("Saved", false);
                Global.jrs.updateRow();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public void insert(PaymentInfo paymentInfo) {
        try {
            String sql = "SELECT * FROM PAYMENT_INFO";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            Global.jrs.moveToInsertRow();
            Global.jrs.updateString("Card_number", paymentInfo.getCardNumber());
            Global.jrs.updateString("Username", paymentInfo.getUsername());
            Global.jrs.updateString("Holder", paymentInfo.getHolder());
            Global.jrs.updateDate("Exp_date", paymentInfo.getExpDate());
            Global.jrs.updateString("Cvv", paymentInfo.getCvv());
            Global.jrs.updateBoolean("Saved", paymentInfo.isSaved());
            Global.jrs.insertRow();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public ArrayList<PaymentInfo> getFromUsername(String username) {
        ArrayList<PaymentInfo> result = new ArrayList<PaymentInfo>();
        try {
            String sql = "SELECT * FROM PAYMENT_INFO WHERE Saved = 1 AND Username = '" + username + "'";
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

    private PaymentInfo getFromRow(JdbcRowSetImpl jrs) {
        PaymentInfo paymentInfo = null;
        try {
            paymentInfo = new PaymentInfo();
            paymentInfo.setUsername(jrs.getString("Username"));
            paymentInfo.setCardNumber(jrs.getString("Card_number"));
            paymentInfo.setCvv(jrs.getString("Cvv"));
            paymentInfo.setExpDate(jrs.getDate("Exp_date"));
            paymentInfo.setHolder(jrs.getString("Holder"));
            paymentInfo.setSaved(jrs.getBoolean("Saved"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return paymentInfo;
    }

}
