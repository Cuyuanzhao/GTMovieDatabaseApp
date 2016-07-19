import com.sun.rowset.JdbcRowSetImpl;
import java.sql.*;
/**
 * Created by cuyuan on 7/8/16.
 */
public class Global {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Team_28"; //"jdbc:mysql://localhost/cs4400_Team_28";
    private static final String USER = "cs4400_Team_28"; //"root";
    private static final String PASSWORD = "8iM4ZHfB";
    public static final int XPOS = 600;
    public static final int YPOS = 300;
    private static String Username;
    public static JdbcRowSetImpl jrs = null;

    static {
        try {
            Class.forName(DRIVER);
            jrs = new JdbcRowSetImpl(URL, USER, PASSWORD);
            /**
             * initial the system info;
             */
            String sql = "SELECT * FROM SYSTEM_INFO";
            jrs.setCommand(sql);
            jrs.execute();
            if (jrs.next()) {
                SystemInfo.setMpassword(Global.jrs.getString("Mpassword"));
                SystemInfo.setCancelFee(Global.jrs.getDouble("Cancel_fee"));
                SystemInfo.setChildDiscount(Global.jrs.getDouble("Child_discount"));
                SystemInfo.setSeniorDiscount(Global.jrs.getDouble("Senior_discount"));
            }
        } catch (SQLException | ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static String getMovieLenghString(int length) {
        int hour = length/60;
        int min = length%60;
        return hour + " hr " + min + " min";

    }
}
