import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/14/16.
 */
public class PreferTheaterBean {
    public ArrayList<String> getSavedTheaterFromMovieTitle(String username, String moiveTitle) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM PREFERS WHERE Username = '" + username + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            while (Global.jrs.next()) {
                result.add(Global.jrs.getString("Theater_id"));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}
