package ui;
import dao.UserDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.User;
import service.AuthService;
public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginFrame() {
        setTitle("Shopkeeper Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        messageLabel = new JLabel("", JLabel.CENTER);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // empty cell
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Simple validation before calling service
                if (username.isEmpty() || password.isEmpty()) {
                    showMessage("Please enter both username and password.");
                    return;
                }

                // TODO: Connect to AuthService (or UserDAO directly) for real authentication
                AuthService service= new AuthService();
                service.loginUser(username, password);
                UserDAO userDAO = new UserDAO();
                User user=userDAO.login(username, password);
                System.out.println(user);
                if (user==null) {
                    showMessage("Invalid credentials. Try again.");
                } else {
                    showMessage("Login successful!");
                }
                
            }
        });
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
    }
}  
