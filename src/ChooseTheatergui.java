import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/14/16.
 */
public class ChooseTheaterGui extends JFrame{
    private JPanel panelRoot;
    private JComboBox chooseComboBox;
    private JButton chooseButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private String movieTitle;
    private ArrayList<String> theaterIdArray;

    public ChooseTheaterGui() throws HeadlessException {
        super("Choose Theater");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseComboBox.setSize(7, chooseComboBox.getHeight());

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String theaterId = getTheaterIdArray().get(chooseComboBox.getSelectedIndex());
                setVis(false);
                Singleton.getSelectTime().SelectTimeInit(movieTitle, theaterId);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchButton.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input your search criteria!");
                }
                else {
                    String keyWord = searchTextField.getText();
                    ArrayList<Theater> searchResult = new TheaterBean().getSearchTheater(keyWord, movieTitle);
                    if (searchResult.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No theater found!");
                    }
                    else {
                        setVis(false);
                        Singleton.getChooseTheaterResult().ChooseTheaterResultInit(searchResult, movieTitle);
                        // TODO: 7/14/16 arguments: @searchResult @movieTitle;
                    }
                }
            }
        });
    }

    public void ChooseTheaterGuiInit(String movieTitle) {
        this.setMovieTitle(movieTitle);
        DefaultComboBoxModel<String> dcm = getDataModel(movieTitle);
        chooseComboBox.setModel(dcm);

        setVis(true);
    }

    private DefaultComboBoxModel<String> getDataModel (String movieTitle) {
        ArrayList<String> savedTheaterId = new PrefersBean().getSavedTheaterFromMovieTitle(Global.getUsername(), getMovieTitle());
        this.setTheaterIdArray(savedTheaterId);
        String[] data = new String[savedTheaterId.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = new TheaterBean().getFromTheaterId(savedTheaterId.get(i)).getName();
        }
        DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(data);
        return dcm;
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

    public ArrayList<String> getTheaterIdArray() {
        return theaterIdArray;
    }

    public void setTheaterIdArray(ArrayList<String> theaterIdArray) {
        this.theaterIdArray = theaterIdArray;
    }




    public static void main(String[] args) {
        Global.setUsername("cuyuanzhao");
        new ChooseTheaterGui().ChooseTheaterGuiInit("Iron man");
    }
}
