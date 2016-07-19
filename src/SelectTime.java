import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cuyuan on 7/15/16.
 */
public class SelectTime extends JFrame {
    private JTabbedPane tabbedPane;
    private JPanel panelRoot;
    private JButton nextButton;
    private JLabel movieTitleLabel;
    private JLabel ratingLengthLabel;
    private JLabel genreLabel;
    private JPanel[] tabPanels;

    private Timestamp showTime = null;
    private String movieTitle;
    private String theaterId;

    public SelectTime() throws HeadlessException {
        super("Select Time");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showTime != null) {
                    setVis(false);
                    Singleton.getBuyTicketGui().BuyTicketGuiInit(movieTitle, theaterId, showTime);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please select a show time!");
                }
            }
        });
    }

    public void SelectTimeInit(String movieTitle, String theaterId) {
        this.setMovieTitle(movieTitle);
        this.setTheaterId(theaterId);

        Movie movie = new MovieBean().getFromMovieTitle(movieTitle);
        movieTitleLabel.setText(movie.getMovieTitle());
        ratingLengthLabel.setText(movie.getRating() + ", " + Global.getMovieLenghString((int)movie.getLength()));
        genreLabel.setText("Genre: " + movie.getGenre());
        setTabPanels();
        setTabbedPane();
        setVis(true);
    }

    private void setTabPanels() {


        this.tabPanels = new JPanel[7];
        for (int i = 0; i < 7; i++) {
            tabPanels[i] = new JPanel(new FlowLayout());
        }
        ArrayList<Timestamp> showTimeArray = new PlaysAtBean().getShowTime(movieTitle, theaterId);
        int currentDay = new Date().getDate();
        for (Timestamp ts: showTimeArray) {
            int dayDiff = ts.getDate() - currentDay;
            if (dayDiff >= 0 && dayDiff < 7) {
                JButton newButton = new JButton(ts.getHours() + ":" + ts.getMinutes());
                newButton.addActionListener(new ActionListener(){
                    /**
                     * set the showTime;
                     * @param e
                     */
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        setShowTime(ts);// how could it be possible
                    }
                });
                tabPanels[dayDiff].add(newButton);
            }
        }

    }

    private void setTabbedPane() {
        Date currentDate = new Date();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        for (int i = 0; i < tabPanels.length; i++) {
            Date tabDate = new Date(currentDate.getTime() + i*24*3600*1000);
            String tabName = months[tabDate.getMonth()] + " " + tabDate.getDate();
            tabbedPane.addTab(tabName, tabPanels[i]);
        }
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    private void setShowTime(Timestamp showTime) {
        this.showTime = showTime;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

}
