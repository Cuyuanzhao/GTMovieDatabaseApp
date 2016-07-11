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
                // TODO: 7/9/16 Order history page; 
            }
        });
        myPaymentInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 7/9/16  payment info page;
            }
        });
        myPreferredTheatersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 7/9/16 preferred theaters page; 
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
