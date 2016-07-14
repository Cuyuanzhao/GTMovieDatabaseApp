import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/13/16.
 */
public class ReviewBean {

    public void insert(Review review) {
        try {
            String sql = "SELECT * FROM REVIEW";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            Global.jrs.moveToInsertRow();
            Global.jrs.updateString("Username", review.getUsername());
            Global.jrs.updateString("Mtitle", review.getMovieTitle());
            Global.jrs.updateString("Rtitle", review.getReviewTitle());
            Global.jrs.updateString("Comments", review.getComments());
            Global.jrs.updateInt("Rating", review.getRating());
            Global.jrs.insertRow();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public int getCountFromMtitle(String movieTitle) {
        int result = -1;
        try {
            String sql = "SELECT COUNT(*) FROM REVIEW WHERE Mtitle = '" + movieTitle + "'";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                result = Global.jrs.getInt(1);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

    public ArrayList<Review> getFromMovieTitle(String movieTitle) {
        ArrayList<Review> result = new ArrayList<Review>();
        try {
            String sql = "SELECT * FROM REVIEW WHERE Mtitle = '" + movieTitle + "'";
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

    private Review getFromRow(JdbcRowSetImpl jrs) {
        Review result = new Review();
        try {
            result.setReviewId(jrs.getInt("Review_id"));
            result.setUsername(jrs.getString("Username"));
            result.setMovieTitle(jrs.getString("Mtitle"));
            result.setReviewTitle(jrs.getString("Rtitle"));
            result.setComments(jrs.getString("Comments"));
            result.setRating(jrs.getInt("Rating"));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return result;
    }

}
