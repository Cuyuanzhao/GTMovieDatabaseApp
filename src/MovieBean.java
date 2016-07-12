import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

/**
 * Created by cuyuan on 7/12/16.
 */
public class MovieBean {

    public Movie getFromMovieTitle(String movieTitle) {
        Movie result = null;
        try {
            String sql = "SELECT * FROM MOVIE WHERE Mtitle = '" + movieTitle + "'";
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

    private Movie getFromRow(JdbcRowSetImpl jrs) {
        Movie result = new Movie();
        try {
            result.setMovieTitle(jrs.getString("Mtitle"));
            result.setCast(jrs.getString("Cast"));
            result.setGenre(jrs.getString("Genre"));
            result.setLength(jrs.getDouble("Movie_length"));
            result.setDate(jrs.getDate("Release_date"));
            result.setAvgRating(jrs.getDouble("Avg_rating"));
            result.setRating(jrs.getString("Rating"));
            result.setSynopsis(jrs.getString("Synopsis"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }
}
