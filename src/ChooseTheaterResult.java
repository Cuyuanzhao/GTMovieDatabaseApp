import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/14/16.
 */
public class ChooseTheaterResult extends JFrame {
    private JPanel panelRoot;
    private JTable searchTheaterTable;
    private JCheckBox saveThisTheaterCheckBox;
    private JButton nextButton;
    private JPanel labelPanel;
    private JLabel searchResultLabel;
    private DefaultTableModel dtm;
    private int preTheaterIndex = -1;
    private int theaterIndex = -1;
    private String movieTitle;
    private ArrayList<Theater> searchResult;

    public ChooseTheaterResult() {
        super("Results");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelPanel.setMaximumSize(new Dimension(50, labelPanel.getWidth()));

        searchTheaterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchTheaterTable.setRowMargin(5);
        searchTheaterTable.setRowHeight(searchTheaterTable.getRowHeight()*2 + 15);
        searchTheaterTable.setDefaultRenderer(String.class, new MultiLineCellRenderer());
        searchTheaterTable.getTableHeader().setVisible(false);
        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
            @Override
            public Class getColumnClass(int col) {
                if (col == 0) {
                    return Boolean.class;
                }
                else {
                    return String.class;
                }
            }
        };

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (theaterIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a theater!");
                }
                else {
                    // insert the prefers to relation Prefers:
                    String theaterId = searchResult.get(theaterIndex).getTheaterId();
                    if (saveThisTheaterCheckBox.isSelected()) {
                        Prefers prefers = new Prefers();
                        prefers.setUsername(Global.getUsername());
                        prefers.setTheaterId(theaterId);
                        new PrefersBean().insert(prefers);
                    }
                    setVis(false);
                    Singleton.getSelectTime().SelectTimeInit(movieTitle, theaterId);
                }
            }
        });
        searchTheaterTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                theaterIndex = searchTheaterTable.rowAtPoint(e.getPoint());
                if (preTheaterIndex != -1) {
                    dtm.setValueAt(false, preTheaterIndex, 0);
                }
                preTheaterIndex = theaterIndex;
                dtm.setValueAt(true, theaterIndex, 0);
            }
        });
    }

    public void ChooseTheaterResultInit(ArrayList<Theater> searchResult, String movieTitle) {
        theaterIndex = -1;
        
        setMovieTitle(movieTitle);
        setSearchResult(searchResult);

        setDataModel(searchResult);
        searchTheaterTable.setModel(dtm);
        searchTheaterTable.getColumnModel().getColumn(0).setMaxWidth(60);
        searchTheaterTable.setPreferredScrollableViewportSize(searchTheaterTable.getPreferredSize());


        setVis(true);
    }

    private void setDataModel(ArrayList<Theater> searchResult) {
        String[] columnName = {"Selection", "Theater Info"};
        int rowCount = searchResult.size();
        Object[][] rows = new Object[rowCount][2];
        for (int i = 0; i < rowCount; i++) {
            Theater theater = searchResult.get(i);
            rows[i][0] = false;
            rows[i][1] = "Theater: " + theater.getName() + "\nAddress: " + theater.getStreet() + ", " + theater.getCity() + ", " + theater.getState() + " " + theater.getZip();
        }
        dtm.setDataVector(rows, columnName);
    }

    private void setVis(boolean b) {
        setVisible(b);
    }

    public int getPreTheaterIndex() {
        return preTheaterIndex;
    }

    public void setPreTheaterIndex(int preTheaterIndex) {
        this.preTheaterIndex = preTheaterIndex;
    }

    public int getTheaterIndex() {
        return theaterIndex;
    }

    public void setTheaterIndex(int theaterIndex) {
        this.theaterIndex = theaterIndex;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public ArrayList<Theater> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(ArrayList<Theater> searchResult) {
        this.searchResult = searchResult;
    }
}
