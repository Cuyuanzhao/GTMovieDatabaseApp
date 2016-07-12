import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by cuyuan on 7/10/16.
 */
public class OrderHistory extends JFrame {
    private JTextField orderIdField;
    private JPanel panel1;
    private JTable orderTable;
    private JPanel panelRoot;
    private JButton viewDetailButton;
    private JButton allOrdersButton;
    private JButton backButton;
    private JButton searchButton;
    private DefaultTableModel dtm;
    private String[] columnName = {"Select", "Order ID", "Movie", "Status", "Total Cost"};
    private int orderId = -1;
    private int preRow = -1;

    public OrderHistory() {
        super("Order History");
        this.setContentPane(panelRoot);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        orderIdField.setColumns(10);

        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dtm = new DefaultTableModel() {
            /*@Override
            public boolean isCellEditable(int row, int col) {
                if (col == 0) return true;
                return false;
            }*/
            @Override
            public Class getColumnClass(int col) {
                if (col == 0)
                    return Boolean.class;
                return Object.class;
            }
        };

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDataModel(new OrdersBean().getFromOrderId(getOrderId()));
                orderTable.setModel(dtm);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getMyInfo().setVisible(true);
            }
        });
        allOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderHistoryInit();
            }
        });
        viewDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderId == -1)
                    return;
                else {
                    setVis(false);
                    Singleton.getOrderDetail().OrderDetailInit(orderId);
                }
                // TODO: 7/11/16
            }
        });
        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row = orderTable.rowAtPoint(e.getPoint());
                orderId = (int)dtm.getValueAt(row, 1);
                if (preRow != -1)
                    dtm.setValueAt(false, preRow, 0);
                dtm.setValueAt(true, row, 0);
                preRow = row;
            }
        });
    }

    public static void main(String[] args) {
        Global.setUsername("cuyuanzhao");
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.OrderHistoryInit();
    }
    /**
     * Initial this orderHistoryTalbe;
     * need to intitial every time when goto this page;
     * @param username
     */
    public void OrderHistoryInit() {
        orderId = -1;
        ArrayList<Orders> orders = new OrdersBean().getFromUsername(Global.getUsername());
        setDataModel(orders);
        orderTable.setModel(dtm);
        setVisible(true);
    }


    private void setDataModel(Orders order) {
        if (order != null) {
            Object[][] rows = {{false, order.getOrderId(), order.getMovieTitle(), order.getStatus(), order.getTotalCost()}};
            dtm.setDataVector(rows, columnName);
        }
        else {
            Object[][] rows = null;
            dtm.setDataVector(rows, columnName);
        }
    }

    private void setDataModel(ArrayList<Orders> ordersArrayList) {
        if (ordersArrayList == null) {
            Object[][] rows = null;
            dtm.setDataVector(rows, columnName);
            return;
        }
        int size = ordersArrayList.size();
        Object[][] rows = new Object[size][5];
        for (int i = 0; i < size; i++) {
            Orders order = ordersArrayList.get(i);
            rows[i][0] = false;
            rows[i][1] = order.getOrderId();
            rows[i][2] = order.getMovieTitle();
            rows[i][3] = order.getStatus();
            rows[i][4] = order.getTotalCost();
        }
        dtm.setDataVector(rows, columnName);
    }

    /**
     * Fromt the textField get the orderId;
     * @return orderId;
     */
    private int getOrderId() {
        int orderId;
        try {
            if (orderIdField.getText().isEmpty()) {
                orderId = -1;
            }
            else {
                orderId = Integer.parseInt(orderIdField.getText());
            }
        } catch (NumberFormatException exc) {
            orderId = -1;
        }
        return orderId;
    }

    /**
     * set this gui visible or hidden;
     * @param b
     */
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
