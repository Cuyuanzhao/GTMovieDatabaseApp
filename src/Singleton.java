/**
 * Created by cuyuan on 7/8/16.
 */
public class Singleton {
    private static LogIn logIn = null;
    private static Register register = null;
    private static NowPlaying nowPlaying = null;
    private static MyInfo myInfo = null;

    public static LogIn getLogIn() {
        if (logIn == null) {
            logIn = new LogIn();
        }
        return logIn;
    }

    public static Register getRegister() {
        if (register == null) {
            register = new Register();
        }
        return register;
    }

    public static NowPlaying getNowPlaying() {
        if (nowPlaying == null) {
            nowPlaying = new NowPlaying();
        }
        return nowPlaying;
    }

    public static MyInfo getMyInfo() {
        if (myInfo == null) {
            myInfo = new MyInfo();
        }
        return myInfo;
    }
}
