import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/13/16.
 */
public class OverviewGui extends JFrame{
    private JButton backButton;
    private JTextPane synopsisTextPane;
    private JTextPane castTextPane;
    private JLabel movieTitleLabel;
    private JPanel panelRoot;
    private String movieTitle;

    public OverviewGui() {
        super("Overview");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 350);
        synopsisTextPane.setEditable(false);
        castTextPane.setEditable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMovieGui().MovieGuiInit(movieTitle);
            }
        });
    }
    public void OverviewGuiInit(Movie movie) {
        movieTitle = movie.getMovieTitle();
        movieTitleLabel.setText(movie.getMovieTitle());
        synopsisTextPane.setText(movie.getSynopsis());
        castTextPane.setText(movie.getCast());
        setVis(true);
    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }

}
