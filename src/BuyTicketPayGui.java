import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by cuyuan on 7/16/16.
 */
public class BuyTicketPayGui extends JFrame{
    private JLabel movieTitleLabel;
    private JLabel ratingLengthLabel;
    private JLabel showTimeLabel;
    private JLabel theaterNameLabel;
    private JLabel streetLable;
    private JLabel addressLabel;
    private JButton buyTicketButton;
    private JComboBox savedCardComboBox;
    private JTextField holderTextField;
    private JTextField cardNumberTextField;
    private JTextField cvvTextField;
    private JTextField expDateTextField;
    private JCheckBox saveThisCardForCheckBox;
    private JButton submitButton;
    private JPanel panelRoot;

    private Orders order;
    private Movie movie;
    private Theater theater;

    public BuyTicketPayGui() {
        super("Payment Information");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        savedCardComboBox.setSize(15, savedCardComboBox.getHeight());

        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.setCardNumber(savedCardComboBox.getSelectedItem().toString());
                order.setOrderId(new OrdersBean().insert(order));
                setVis(false);
                Singleton.getBuyTicketConfirmGui().BuyTicketConfirmGuiInit(movie, theater, order);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide a valid credit card!");
                }
                else {
                    if (Date.valueOf(expDateTextField.getText()).after(new Date(Calendar.getInstance().getTimeInMillis()))) {
                        PaymentInfo paymentInfo = new PaymentInfo();
                        paymentInfo.setUsername(Global.getUsername());
                        paymentInfo.setHolder(holderTextField.getText());
                        paymentInfo.setCardNumber(cardNumberTextField.getText());
                        paymentInfo.setCvv(cvvTextField.getText());
                        paymentInfo.setSaved(false);
                        paymentInfo.setExpDate(Date.valueOf(expDateTextField.getText()));
                        if (saveThisCardForCheckBox.isSelected()) {
                            paymentInfo.setSaved(true);
                        }
                        new PaymentInfoBean().insert(paymentInfo);
                        order.setCardNumber(cardNumberTextField.getText());
                        order.setOrderId(new OrdersBean().insert(order));
                        setVis(false);
                        Singleton.getBuyTicketConfirmGui().BuyTicketConfirmGuiInit(movie, theater, order);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "This card has expired!");
                    }

                }
            }
        });
    }

    public void BuyTicketPayGuiInit(Movie movie, Theater theater, Orders order) {
        ArrayList<PaymentInfo> paymentInfos = new PaymentInfoBean().getFromUsername(Global.getUsername());
        savedCardComboBox.setModel(getDateMode(paymentInfos));
        this.setOrder(order);
        this.setMovie(movie);
        this.setTheater(theater);
        
        movieTitleLabel.setText(movie.getMovieTitle());
        ratingLengthLabel.setText(movie.getRating() + ", " + movie.getLength());
        showTimeLabel.setText(order.getDate() + " " + order.getTime());
        theaterNameLabel.setText(theater.getName());
        streetLable.setText(theater.getStreet());
        addressLabel.setText(theater.getCity() + ", " + theater.getState() + " " + theater.getZip());

        setVis(true);
    }
    
    private DefaultComboBoxModel<String> getDateMode(ArrayList<PaymentInfo> paymentInfos) {
        String[] data = new String[paymentInfos.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = paymentInfos.get(i).getCardNumber();
        }
        DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(data);
        return dcm;
    }

    private boolean isEmpty() {
        return cardNumberTextField.getText().isEmpty() || cvvTextField.getText().isEmpty() || holderTextField.getText().isEmpty() || expDateTextField.getText().isEmpty();
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public void setMovieTitleLabel(JLabel movieTitleLabel) {
        this.movieTitleLabel = movieTitleLabel;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
