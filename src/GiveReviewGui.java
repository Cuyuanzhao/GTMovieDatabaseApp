import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by cuyuan on 7/14/16.
 */
public class GiveReviewGui extends JFrame {
    private JPanel panelRoot;
    private JLabel movieTitleLabel;
    private JButton submitButton;
    private JButton backButton;
    private JTextField titleTextField;
    private JTextPane commentTextPane;
    private JComboBox ratingComboBox;
    private String movieTitle;

    public GiveReviewGui() {
        super("Give Review");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Integer[] option = {5, 4, 3, 2, 1};
        DefaultComboBoxModel<Integer> dcm = new DefaultComboBoxModel<Integer>(option);
        ratingComboBox.setModel(dcm);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please give the Rating and Title!");
                }
                else {
                    new ReviewBean().insert(getReview());
                    JOptionPane.showMessageDialog(null, "Succeed!");
                    // TODO: 7/14/16 update review rather than insert revivew repeatly.
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getReviewGui().ReviewGuiInit(movieTitle);
            }
        });
    }

    public void GiveReviewGuiInit(String movieTitle) {
        setMovieTitle(movieTitle);
        movieTitleLabel.setText(movieTitle);
        setVis(true);
    }

    private Review getReview() {
        Review review = new Review();
        review.setRating((int)ratingComboBox.getSelectedItem());
        review.setUsername(Global.getUsername());
        review.setReviewTitle(titleTextField.getText());
        review.setComments(commentTextPane.getText());
        review.setMovieTitle(movieTitle);
        return review;
    }

    private boolean isEmpty() {
        return titleTextField.getText().isEmpty();
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitel) {
        this.movieTitle = movieTitel;
    }
}
