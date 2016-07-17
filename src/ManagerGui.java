import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/17/16.
 */
public class ManagerGui extends JFrame{
    private JPanel panelRoot;
    private JButton logOutButton;
    private JButton viewRevenueReportButton;
    private JButton viewPopularMovieReportButton;

    public ManagerGui() {
        super("Manager view");
        this.setContentPane(panelRoot);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getLogIn().setVisible(true);
            }
        });
        viewRevenueReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getRevenueReportGui().setVisible(true);
            }
        });
        viewPopularMovieReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Global.setUsername(null);
                Singleton.getPopularMovieGui().setVisible(true);
            }
        });
    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
