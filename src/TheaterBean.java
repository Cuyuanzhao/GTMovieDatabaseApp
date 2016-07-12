import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

/**
 * Created by cuyuan on 7/12/16.
 */
public class TheaterBean {

    public Theater getFromTheaterId (String theaterId) {
        Theater result = null;
        try {
            String sql = "SELECT * FROM THEATER WHERE Theater_id = '" + theaterId + "'";
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

    private Theater getFromRow (JdbcRowSetImpl jrs) {
        Theater result = new Theater();
        try {
            result.setTheaterId(jrs.getString("Theater_id"));
            result.setName(jrs.getString("Name"));
            result.setStreet(jrs.getString("Street"));
            result.setCity(jrs.getString("City"));
            result.setState(jrs.getString("State"));
            result.setZip(jrs.getString("Zip"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}
