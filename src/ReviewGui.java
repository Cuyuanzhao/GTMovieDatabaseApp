import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by cuyuan on 7/13/16.
 */
public class ReviewGui extends JFrame {
    private JPanel panelRoot;
    private JLabel movieTitleLabel;
    private JLabel avgRatingLabel;
    private JButton giveReviewButton;
    private JButton backButton;
    private JTable reviewTable;
    private String movieTitle;
    private DefaultTableModel dtm;

    public ReviewGui() throws HeadlessException {
        super("Review");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 350);
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
            @Override
            public Class getColumnClass(int col) {
                return String.class;
            }
        };

        reviewTable.setRowMargin(5);
        reviewTable.setRowHeight(reviewTable.getRowHeight()*3 + 5);
        reviewTable.setDefaultRenderer(String.class, new MultiLineCellRenderer());
        reviewTable.setTableHeader(null);


        giveReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new OrdersBean().canGiveReview(Global.getUsername(), movieTitle)) {
                    setVis(false);
                    Singleton.getGiveReviewGui().GiveReviewGuiInit(movieTitle);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMovieGui().MovieGuiInit(movieTitle);
            }
        });
    }

    public void ReviewGuiInit(String movieTitle) {
        setMovieTitle(movieTitle);

        Movie movie = new MovieBean().getFromMovieTitle(movieTitle);
        movieTitleLabel.setText(movie.getMovieTitle());
        avgRatingLabel.setText("Avg Rating: " + movie.getAvgRating());

        if (!(new OrdersBean().canGiveReview(Global.getUsername(), movieTitle))) {
            giveReviewButton.setForeground(new Color(0, 0, 0, 100));
        }

        this.setDataModel(movieTitle);

        reviewTable.setModel(dtm);

        setVis(true);
    }

    private void setDataModel(String movieTitle) {
        ArrayList<Review> reviewArray = new ReviewBean().getFromMovieTitle(movieTitle);
        String[][] rows = new String[reviewArray.size()][1];
        for (int i = 0; i < reviewArray.size(); i++) {
            Review review = reviewArray.get(i);
            rows[i][0] = "Title: " + review.getReviewTitle() + "\nRating: " + review.getRating() + "\nComment: " + review.getComments();
        }
        String[] columnName = {"Review Lists"};
        dtm.setDataVector(rows, columnName);
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

}

