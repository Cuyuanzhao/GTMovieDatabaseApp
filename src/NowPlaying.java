import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/9/16.
 */
public class NowPlaying extends JFrame {
    private JPanel panel1;
    private JButton meButton;
    private JTable nowPlayingTable;
    private DefaultTableModel dtm;

    public NowPlaying() {
        super("Now Playing");
        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        /**
         * Initial the JTable
         */
        String[] columnName = {"Movie Title", "Movie Title"};
        ArrayList<String> nowPlaying = new PlaysAtBean().getNowPlaying();
        Object[][] rows = new String[(nowPlaying.size() + 1)/2][2];
        for (int i = 0; i < nowPlaying.size(); i++) {
            rows[i/2][i%2] = nowPlaying.get(i);
        }
        dtm.setDataVector(rows, columnName);
        nowPlayingTable.setModel(dtm);
        nowPlayingTable.setCellSelectionEnabled(true);

        meButton.addActionListener(new ActionListener() {
            /**
             * go to MyInfo Page;
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMyInfo().setVisible(true);
            }
        });

        nowPlayingTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = nowPlayingTable.rowAtPoint(e.getPoint());
                int col = nowPlayingTable.columnAtPoint(e.getPoint());
                String Mtitle = (String)dtm.getValueAt(row, col);
                System.out.println(Mtitle);
                // TODO: 7/9/16 use the Mtitle initialize another page;
            }
        });

    }

    private void setVis(boolean b) {
        setVisible(b);
    }

}
