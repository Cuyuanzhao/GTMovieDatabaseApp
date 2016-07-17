import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/9/16.
 */
public class MyInfo extends JFrame {
    private JButton myOrderHistoryButton;
    private JButton myPreferredTheatersButton;
    private JButton myPaymentInformationButton;
    private JButton backButton;
    private JPanel jPanel;

    public MyInfo() {
        super("MyInfo");
        this.setContentPane(jPanel);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myOrderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Singleton.getOrderHistory().OrderHistoryInit();
            }
        });
        myPaymentInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMyPaymentInfoGui().MyPaymentInfoGuiInit();
            }
        });
        myPreferredTheatersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMyPreferredTheaterGui().MyPreferredTheaterGuiInit();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getNowPlaying().setVisible(true);
            }
        });

    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
