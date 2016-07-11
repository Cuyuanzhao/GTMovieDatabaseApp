import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuyuan on 7/8/16.
 */
public class Register extends JFrame {
    private JPanel panel1;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;
    private JPasswordField mPasswordField;
    private JButton backButton;
    private JLabel username;
    private JLabel email;
    private JLabel confirm;
    private JLabel password;
    private JLabel mPassword;
    private JButton createButton;

    public Register() {
        super("Register");
        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
        this.setLocation(Global.XPOS, Global.YPOS);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in required fields first!");
                }
                else {
                    if (!isMatch()) {
                        JOptionPane.showMessageDialog(null, "The password is not consistent with the confirmed password!");
                    }
                    else {
                        User newUser = new User();
                        newUser.setEmail(emailField.getText());
                        newUser.setUsername(usernameField.getText());
                        newUser.setPassword(new String(passwordField.getPassword()));
                        if (!(new CustomerBean().isAvailable(newUser)) || !(new ManagerBean().isAvailable(newUser))) {
                            JOptionPane.showMessageDialog(null, "The Username or Email are not available!");
                        }
                        else {
                            if (isEmptyMpassword()) {
                                new CustomerBean().insert(newUser);
                                Global.setUsername(newUser.getUsername());
                                // TODO: 7/9/16  
                            }
                            else {
                                if (isMpasswordMatch()) {
                                    new ManagerBean().insert(newUser);
                                    // TODO: 7/9/16  
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "The manager password is wrong!");
                                }
                            }
                        }
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVis(false);
                Singleton.getLogIn().setVisible(true);
            }
        });
    }

    private void setVis(boolean b) {
        this.setVisible(b);
    }

    private boolean isEmpty() {
        return usernameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getPassword().length == 0 || confirmField.getPassword().length == 0;
    }

    private boolean isMatch() {
        String p = new String(passwordField.getPassword());
        String c = new String (confirmField.getPassword());
        return p.equals(c);
    }

    private boolean isEmptyMpassword() {
        return mPasswordField.getPassword().length == 0;
    }

    private boolean isMpasswordMatch() {
        String mInput = new String(mPasswordField.getPassword());
        String mPassword = new SystemInfoBean().getSystemInfo().getMpassword();
        return mInput.equals(mPassword);
    }
}
