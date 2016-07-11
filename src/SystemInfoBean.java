import java.sql.SQLException;

/**
 * Created by cuyuan on 7/9/16.
 */
public class SystemInfoBean {
    /**
     * Query the SYSTEM_INFO table and store the result to SystemInfo class
     * @return SystemInfo
     */
    public SystemInfo getSystemInfo() {
        SystemInfo systemInfo = null;
        try {
            String sql = "SELECT * FROM SYSTEM_INFO";
            Global.jrs.setCommand(sql);
            Global.jrs.execute();
            if (Global.jrs.next()) {
                systemInfo = new SystemInfo();
                systemInfo.setMpassword(Global.jrs.getString("Mpassword"));
                systemInfo.setCancelFee(Global.jrs.getDouble("Cancel_fee"));
                systemInfo.setChildDiscount(Global.jrs.getDouble("Child_discount"));
                systemInfo.setSeniorDiscount(Global.jrs.getDouble("Senior_discount"));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return systemInfo;
    }

}
