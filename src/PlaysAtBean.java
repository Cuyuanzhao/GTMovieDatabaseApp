import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/9/16.
 */
public class PlaysAtBean {
    private ArrayList<String> nowPlaying = null;
    /**
     * get the nowPlaying movieTitle List;
     * @return
     */
    public ArrayList<String> getNowPlaying() {
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
