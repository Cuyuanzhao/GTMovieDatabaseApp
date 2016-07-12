import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/11/16.
 */
public class OrderDetail extends JFrame {

    private JPanel panelRoot;
    private JPanel panelMovie;
    private JPanel panelTheaterLabel;
    private JButton cancelThisOrderButton;
    private JButton backButton;
    private JLabel theaterName;
    private JLabel streetLabal;
    private JLabel locationLabel;
    private JLabel totalCostLabel;
    private JLabel adultTixLabel;
    private JLabel childTixLabel;
    private JLabel seniorTixLabel;
    private JLabel mtitleLabel;
    private JLabel typeLengthLabel;
    private JLabel dataTimeLabel;

    public OrderDetail() {
        super("Order Detail");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        cancelThisOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getOrderHistory().OrderHistoryInit();
            }
        });
    }

    public void OrderDetailInit(int orderId) {
        Orders order = new OrdersBean().getFromOrderId(orderId);
        if (!order.getStatus().equals("unused")) {
            cancelThisOrderButton.setVisible(false);
        }
        totalCostLabel.setText("Total cost: " + String.valueOf(order.getTotalCost()));
        adultTixLabel.setText(String.valueOf(order.getAdultTix()) + "Adult tickets");
        childTixLabel.setText(String.valueOf(order.getChildTix()) + "Child tickets");
        seniorTixLabel.setText(String.valueOf(order.getSeniorTix()) + "Senior tickets");



        setVisible(true);
    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
