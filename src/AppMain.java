import com.sun.rowset.JdbcRowSetImpl;
import java.sql.*;
/**
 * Created by cuyuan on 7/8/16.
 */
public class AppMain {

    public static void main(String[] args) {


        Singleton singleton = new Singleton();
        LogIn logIn = singleton.getLogIn();
    }
}
