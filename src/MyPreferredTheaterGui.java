import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/17/16.
 */
public class MyPreferredTheaterGui extends JFrame{
    private JPanel panelRoot;
    private JTable theaterTable;
    private JButton deleteButton;
    private JButton backButton;
    private DefaultTableModel dtm;
    private int preRow = -1;
    private ArrayList<Prefers> prefersArrayList;
    private int rowHeight;

    public MyPreferredTheaterGui() {
        super("My Payment Info");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.theaterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.rowHeight = theaterTable.getRowHeight();
        this.dtm = new DefaultTableModel() {
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

        theaterTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (preRow != -1) {
                    dtm.setValueAt(false, preRow, 0);
                }
                preRow = theaterTable.getSelectedRow();
                dtm.setValueAt(true, preRow, 0);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (preRow != -1) {
                    Prefers prefers = prefersArrayList.get(preRow);
                    new PrefersBean().delete(prefers);
                    MyPreferredTheaterGuiInit();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMyInfo().setVisible(true);
            }
        });
    }

    public void MyPreferredTheaterGuiInit() {
        preRow = -1;
        prefersArrayList = new PrefersBean().getFromUsername(Global.getUsername());
        ArrayList<Theater> theaters = new ArrayList<Theater>();
        for (Prefers prefer: prefersArrayList) {
            String theaterId = prefer.getTheaterId();
            theaters.add(new TheaterBean().getFromTheaterId(theaterId));
        }
        this.setDataMode(theaters);
        theaterTable.setModel(dtm);
        theaterTable.getColumnModel().getColumn(0).setMaxWidth(60);
        theaterTable.setRowHeight(rowHeight*2);

        setVis(true);
    }

    private void setDataMode(ArrayList<Theater> theaters) {
        String[] col = {"Select", "Theater Info"};
        Object[][] row = new Object[theaters.size()][2];
        for (int i = 0; i < theaters.size(); i++) {
            Theater temp = theaters.get(i);
            row[i][0] = false;
            row[i][1] = "<html>" + temp.getName() + "<br>" + temp.getStreet() + ", " + temp.getCity() + ", " + temp.getState() + " " + temp.getZip() + "</html>";
        }
        dtm.setDataVector(row, col);
    }

    public void setPrefersArrayList(ArrayList<Prefers> prefersArrayList) {
        this.prefersArrayList = prefersArrayList;
    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
