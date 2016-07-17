import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by cuyuan on 7/9/16.
 */
public class PlaysAtBean {

    public double getPrice(String movieTitle, String theaterId, Timestamp showTime) {
        double price = -1;
        try {
            String sql = "SELECT Price FROM PLAYS_AT WHERE MTitle = '" + movieTitle + "' AND Theater_id = '" + theaterId + "' AND Show_time = '" + showTime.toString() + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                price = Global.jrs.getDouble("Price");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return price;
    }

    /**
     * get the showTime that is larger than currentDate;
     * @param movieTitle
     * @param theaterId
     * @return
     */
    public ArrayList<Timestamp> getShowTime(String movieTitle, String theaterId) {
        ArrayList<Timestamp> result = new ArrayList<Timestamp>();
        try {
            String sql = "SELECT Show_time FROM PLAYS_AT WHERE Playing = 1 AND MTitle = '" + movieTitle + "' AND Theater_id = '" + theaterId + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            Date currentDate = new Date();
            while (Global.jrs.next()) {
                Timestamp showTime = Global.jrs.getTimestamp("Show_time");
                if (showTime.compareTo(currentDate) > 0) {
                    result.add(showTime);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    /**
     * get the nowPlaying movieTitle List;
     * @return
     */
    public ArrayList<String> getNowPlaying() {
        ArrayList<String> nowPlaying = null;
        try {
            String sql = "SELECT DISTINCT Mtitle FROM PLAYS_AT WHERE Playing = 1";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            nowPlaying = new ArrayList<String>();
            while (Global.jrs.next()) {
                nowPlaying.add(Global.jrs.getString("Mtitle"));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return nowPlaying;
    }

}
