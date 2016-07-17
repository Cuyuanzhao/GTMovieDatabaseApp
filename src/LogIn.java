import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/5/16.
 */
public class LogIn extends JFrame{

    private JPanel login_root;
    private JPanel login_panel_root;
    private JPanel login_panel_label;
    private JPanel login_panel_text;
    private JPanel login_panel_button;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton RegisterButton;



    public LogIn() {
        super("LogIn");
        this.setContentPane(login_root);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(Global.XPOS, Global.YPOS);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please input the Username and Password!");
                }
                else {
                    User u = new User();
                    u.setUsername(textField.getText());
                    u.setPassword(new String(passwordField.getPassword()));
                    if (new CustomerBean().isValid(u)) {
                        Global.setUsername(u.getUsername());
                        setVis(false);
                        Singleton.getNowPlaying().setVisible(true);
                    }
                    else if (new ManagerBean().isValid(u)) {
                        setVis(false);
                        Singleton.getManagerGui().setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The Username or Password are wrong");
                    }
                }
            }
        });

        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getRegister().setVisible(true);
            }
        });
    }

    private boolean isEmpty() {
        return textField.getText().isEmpty() || passwordField.getPassword().length == 0;
    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }


}
