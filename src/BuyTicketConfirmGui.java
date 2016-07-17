import javax.swing.*;

/**
 * Created by cuyuan on 7/16/16.
 */
public class BuyTicketConfirmGui extends JFrame{
    private JLabel movieTitleLabel;
    private JLabel ratingLengthLabel;
    private JLabel showTimeLabel;
    private JLabel theaterNameLabel;
    private JLabel streetLable;
    private JLabel addressLabel;
    private JLabel orderIdLabel;
    private JPanel panelRoot;

    public BuyTicketConfirmGui() {
        super("Confirmation");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void BuyTicketConfirmGuiInit(Movie movie, Theater theater, Orders order) {
        movieTitleLabel.setText(movie.getMovieTitle());
        ratingLengthLabel.setText(movie.getRating() + ", " + movie.getLength());
        showTimeLabel.setText(order.getDate() + " " + order.getTime());
        theaterNameLabel.setText(theater.getName());
        streetLable.setText(theater.getStreet());
        addressLabel.setText(theater.getCity() + ", " + theater.getState() + " " + theater.getZip());
        orderIdLabel.setText("Order ID: " + order.getOrderId());
        setVisible(true);
    }
}
