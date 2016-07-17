import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/13/16.
 */
public class MovieGui extends JFrame {
    private JButton overviewButton;
    private JButton movieReviewButton;
    private JButton buyTicketsButton;
    private JLabel movieTitleLabel;
    private JLabel releaseDateLabel;
    private JLabel ratingLengthLabel;
    private JLabel genrelLabel;
    private JLabel avgRatingLabel;
    private JLabel reviewCountLabel;
    private JPanel panelRoot;
    private JButton backButton;
    private String movieTitle;
    private Movie movie;


    public MovieGui() throws HeadlessException {
        super("Movie");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        overviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getOverviewGui().OverviewGuiInit(movie);
            }
        });
        movieReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getReviewGui().ReviewGuiInit(movieTitle);
            }
        });
        buyTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getChooseTheaterGui().ChooseTheaterGuiInit(movieTitle);
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

    public void MovieGuiInit(String movieTitle) {
        setMovieTitle(movieTitle);

        Movie movie = new MovieBean().getFromMovieTitle(movieTitle);
        setMovie(movie);
        movieTitleLabel.setText(movie.getMovieTitle());
        releaseDateLabel.setText(movie.getDate().toString());
        ratingLengthLabel.setText(movie.getRating() + ", " + movie.getLength());
        genrelLabel.setText(movie.getGenre());
        avgRatingLabel.setText("Average Score: " + movie.getAvgRating());
        reviewCountLabel.setText("" + (new ReviewBean().getCountFromMtitle(movieTitle)) + " Fan Ratings");

        setVisible(true);
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }


    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
