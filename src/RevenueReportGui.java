import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by cuyuan on 7/17/16.
 */
public class RevenueReportGui extends JFrame{
    private JPanel panelRoot;
    private JTable revenueTable;
    private JButton backButton;

    public RevenueReportGui() {
        super("Revenue Report");
        this.setContentPane(panelRoot);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[] col = {"Month", "Revenue"};
        Object[][] row = new OrdersBean().getRevenueReport();
        revenueTable.setModel(new DefaultTableModel(row, col));


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getManagerGui().setVisible(true);
            }
        });
    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
