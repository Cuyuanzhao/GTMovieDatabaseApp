import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/12/16.
 */
public class TheaterBean {

    public ArrayList<Theater> getSearchTheater(String keyWord, String movieTitle) {
        ArrayList<Theater> result = new ArrayList<Theater>();
        try {
            // TODO: 7/16/16 need to compare the showtime with current date;
            String sql = "SELECT DISTINCT THEATER.Theater_id, Name, Street, City, State, Zip FROM THEATER JOIN PLAYS_AT ON THEATER.Theater_id = PLAYS_AT.Theater_id WHERE Playing = 1 AND MTitle = '" + movieTitle + "' AND (Name = '" + keyWord + "' OR City = '" + keyWord + "' OR State = '" + keyWord + "')";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            while (Global.jrs.next()) {
                Theater theater = getFromRow(Global.jrs);
                result.add(theater);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

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
