import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by cuyuan on 7/17/16.
 */
public class MyPaymentInfoGui extends JFrame{
    private JButton deleteButton;
    private JButton backButton;
    private JTable paymentInfoTable;
    private JPanel panelRoot;
    private DefaultTableModel dtm;
    private int preRow = -1;



    public MyPaymentInfoGui() {
        super("My Payment Info");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.paymentInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (preRow != -1) {
                    String cardNumber = dtm.getValueAt(preRow, 1).toString();
                    new PaymentInfoBean().unSave(cardNumber, Global.getUsername());
                    MyPaymentInfoGuiInit();
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
        paymentInfoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (preRow != -1) {
                    dtm.setValueAt(false, preRow, 0);
                }
                preRow = paymentInfoTable.getSelectedRow();
                dtm.setValueAt(true, preRow, 0);
            }
        });
    }

    public void MyPaymentInfoGuiInit() {
        this.setPreRow(-1);
        ArrayList<PaymentInfo> savedPaymentInfo = new PaymentInfoBean().getFromUsername(Global.getUsername());
        this.setDataModel(savedPaymentInfo);
        paymentInfoTable.setModel(dtm);
        paymentInfoTable.getColumnModel().getColumn(0).setMaxWidth(60);


        setVis(true);
    }

    private void setDataModel(ArrayList<PaymentInfo> paymentInfos) {
        String[] col = {"Select", "Card Number", "Name on Card", "Exp Date"};
        Object[][] row = new Object[paymentInfos.size()][col.length];
        for (int i = 0; i < paymentInfos.size(); i++) {
            PaymentInfo temp = paymentInfos.get(i);
            row[i][0] = false;
            row[i][1] = temp.getCardNumber();
            row[i][2] = temp.getHolder();
            row[i][3] = temp.getExpDate();
        }
        dtm.setDataVector(row, col);
    }

    public void setPreRow(int preRow) {
        this.preRow = preRow;
    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }
}
