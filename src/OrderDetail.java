import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/11/16.
 */
public class OrderDetail extends JFrame {

    private JPanel panelRoot;
    private JButton cancelThisOrderButton;
    private JButton backButton;
    private JLabel theaterNameLabel;
    private JLabel streetLabal;
    private JLabel locationLabel;
    private JLabel totalCostLabel;
    private JLabel adultTixLabel;
    private JLabel childTixLabel;
    private JLabel seniorTixLabel;
    private JLabel mtitleLabel;
    private JLabel typeLengthLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private int orderId = -1;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderDetail() {
        super("Order Detail");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        cancelThisOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to cancel this order?", "Warning", option);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    new OrdersBean().cancelOrder(orderId);
                    cancelThisOrderButton.setVisible(false);
                }
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
        setOrderId(orderId);

        Orders order = new OrdersBean().getFromOrderId(orderId);
        if (order == null) return;
        if (order.getStatus().equals("unused")) {
            cancelThisOrderButton.setVisible(true);
        }
        else {
            cancelThisOrderButton.setVisible(false);
        }
        totalCostLabel.setText("Total cost: $" + String.valueOf(order.getTotalCost()));
        adultTixLabel.setText(String.valueOf(order.getAdultTix()) + " Adult tickets");
        childTixLabel.setText(String.valueOf(order.getChildTix()) + " Child tickets");
        seniorTixLabel.setText(String.valueOf(order.getSeniorTix()) + " Senior tickets");
        dateLabel.setText(order.getDate().toString());
        timeLabel.setText(order.getTime().toString());


        Movie movie = new MovieBean().getFromMovieTitle(order.getMovieTitle());
        mtitleLabel.setText(movie.getMovieTitle());
        typeLengthLabel.setText(movie.getGenre() + movie.getLength());


        Theater theater = new TheaterBean().getFromTheaterId(order.getTheaterId());
        theaterNameLabel.setText(theater.getName());
        streetLabal.setText(theater.getStreet());
        locationLabel.setText(theater.getCity() + ", " + theater.getState() + " " + theater.getZip());

        setVisible(true);
    }

    public static void main(String[] args) {
        Singleton.getOrderDetail().OrderDetailInit(1);
    }
    private void setVis(boolean b) {
        setVisible(b);
    }
}
