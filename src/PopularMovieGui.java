import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by cuyuan on 7/17/16.
 */
public class PopularMovieGui extends JFrame {
    private JButton backButton;
    private JTable popularMovieTable;
    private JPanel panelRoot;

    public PopularMovieGui() {
        super("Popular Movie");
        this.setContentPane(panelRoot);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);

        Object[] col = {"Month", "Movie Title", "#of Orders"};
        Object[][] row = new OrdersBean().getPopularMovie();
        popularMovieTable.setModel(new DefaultTableModel(row, col));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getManagerGui().setVisible(true);
            }
        });
    }

    private void setVis(boolean b) {
        setVisible(b);
    }
}
