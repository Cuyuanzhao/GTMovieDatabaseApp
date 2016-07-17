import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/14/16.
 */
public class PrefersBean {

    public void insert(Prefers prefers) {
        try {
            String sql = "SELECT * FROM PREFERS WHERE Username = '" + prefers.getUsername() + "' AND Theater_id = '" + prefers.getTheaterId() + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (!Global.jrs.next()) {
                Global.jrs.moveToInsertRow();
                Global.jrs.updateString("Username", prefers.getUsername());
                Global.jrs.updateString("Theater_id", prefers.getTheaterId());
                Global.jrs.insertRow();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public ArrayList<String> getSavedTheaterFromMovieTitle(String username, String moiveTitle) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT PRE.Theater_id as id FROM PREFERS AS PRE JOIN PLAYS_AT AS PL ON PRE.Theater_id = PL.Theater_id WHERE Username = '" + username + "' AND Mtitle = '" + moiveTitle + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            while (Global.jrs.next()) {
                result.add(Global.jrs.getString("id"));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

}
