import com.sun.tools.corba.se.idl.constExpr.Times;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.sql.Date;

import static java.lang.StrictMath.round;

/**
 * Created by cuyuan on 7/16/16.
 */
public class BuyTicketGui extends JFrame{
    private JLabel movieTitleLabel;
    private JLabel ratingLengthLabel;
    private JLabel showTimeLabel;
    private JLabel theaterNameLabel;
    private JLabel streetLable;
    private JLabel addressLabel;
    private JSpinner adultSpinner;
    private JSpinner childSpinner;
    private JSpinner seniorSpinner;
    private JPanel panelRoot;
    private JPanel ticketNumPanel;
    private JLabel adultLabel;
    private JLabel seniorLabel;
    private JLabel childLabel;
    private JLabel totalCostLabel;
    private JButton nextButton;

    private Movie movie;
    private Theater theater;
    private Timestamp showTime;
    private Orders order = new Orders();
    private double price;
    private double totalCost;

    public BuyTicketGui() {
        super("Select Time");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        adultSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        seniorSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
        childSpinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalCost == 0) {
                    JOptionPane.showMessageDialog(null, "Please order at least one ticket");
                }
                else {
                    order.setAdultTix((int)adultSpinner.getValue());
                    order.setChildTix((int)childSpinner.getValue());
                    order.setSeniorTix((int)seniorSpinner.getValue());
                    order.setTotalCost(totalCost);
                    setVis(false);
                    Singleton.getBuyTicketPayGui().BuyTicketPayGuiInit(movie, theater, order);
                }
            }
        });
        adultSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setCostLabel();
            }
        });
        seniorSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setCostLabel();
            }
        });
        childSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setCostLabel();
            }
        });
    }

    public void BuyTicketGuiInit(String movieTitle, String theaterId, Timestamp showTime) {
        this.setShowTime(showTime);
        this.setPrice(new PlaysAtBean().getPrice(movieTitle, theaterId, showTime));

        movie = new MovieBean().getFromMovieTitle(movieTitle);
        theater = new TheaterBean().getFromTheaterId(theaterId);
        order.setUsername(Global.getUsername());
        order.setMovieTitle(movieTitle);
        order.setTheaterId(theaterId);
        order.setStatus("unused");
        order.setDate(new Date(showTime.getTime()));
        order.setTime(new Time(showTime.getTime()));

        movieTitleLabel.setText(movieTitle);
        ratingLengthLabel.setText(movie.getRating() + ", " + movie.getLength());
        showTimeLabel.setText(showTime.toString());

        theaterNameLabel.setText(theater.getName());
        streetLable.setText(theater.getStreet());
        addressLabel.setText(theater.getCity() + ", " + theater.getState() + " " + theater.getZip());

        this.setCostLabel();
        setVis(true);
    }
    

    private void setCostLabel() {
        adultLabel.setText("*" + price + " = " + String.format("%.2f", price*(int)adultSpinner.getValue()));
        seniorLabel.setText("*" + price + "*" + (int)(SystemInfo.getSeniorDiscount()*100) + "% = " + String.format("%.2f", price*(int)seniorSpinner.getValue()*SystemInfo.getSeniorDiscount()));
        childLabel.setText("*" + price + "*" + (int)(SystemInfo.getChildDiscount()*100) + "% = " + String.format("%.2f", price*(int)childSpinner.getValue()*SystemInfo.getChildDiscount()));
        totalCost = price*((int)adultSpinner.getValue() + SystemInfo.getSeniorDiscount()*(int)seniorSpinner.getValue() + SystemInfo.getChildDiscount()*(int)childSpinner.getValue());
        totalCost = (double)Math.round(totalCost*100)/100d;
        totalCostLabel.setText("Total Cost: $" + String.format("%.2f", totalCost));
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    public void setMovieTitleLabel(JLabel movitTitleLabel) {
        this.movieTitleLabel = movitTitleLabel;
    }

    public void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
