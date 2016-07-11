import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;

/**
 * Created by cuyuan on 7/8/16.
 */
public class CustomerBean {
    /**
     * Query if the @Username and @Passwords exist in @Customer;
     * @param c
     * @return
     */
    public boolean isValid(User c) {
        boolean result= false;
        try {
            String sql = "SELECT * FROM CUSTOMER WHERE Username = '" + c.getUsername() + "' AND Passwords = '" + c.getPassword() + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            result = Global.jrs.next();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    public boolean isAvailable(User c) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM CUSTOMER WHERE Username = '" + c.getUsername() + "' OR Email = '" + c.getEmail() + "'" ;
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (!Global.jrs.next()) {
                result = true;
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    /**
     * Insert the a record, check if @Username or @Email aready exist;
     * @param c
     * @return error message;
     */
    public String insert(User c) {
        String errorMsg = "Error";
        try {
            String sql = "SELECT * FROM CUSTOMER WHERE Username = '" + c.getUsername() + "' OR Email = '" + c.getEmail() + "'" ;
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                errorMsg = "The Username or Email are not available";
            }
            else {
                Global.jrs.moveToInsertRow();
                Global.jrs.updateString("Username", c.getUsername());
                Global.jrs.updateString("Passwords", c.getPassword());
                Global.jrs.updateString("Email", c.getEmail());
                Global.jrs.insertRow();
                Global.jrs.moveToCurrentRow();
                errorMsg = null;
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return errorMsg;
    }
}
