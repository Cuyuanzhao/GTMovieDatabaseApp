import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/10/16.
 */
public class OrderHistory extends JFrame {
    private JTextField textField1;
    private JButton searchButton;
    private JTable table1;
    private JPanel panel1;

    public OrderHistory() {
        super("Order History");
        this.setContentPane(panel1);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void OrderHistoryInit(String username) {

    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }
}

/*
DefaultTableModel(Object[][] data, Object[] columnNames)
setDataVector(Object[][] dataVector, Object[] columnIdentifiers)
addRow(Object[] rowData)

table.getModel().setValueAt(cellValueObject, rowIndex, colIndex);
*/
