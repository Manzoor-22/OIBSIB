import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        //Login page
        super("Login Page");
        setBounds(750, 400, 400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("icon.jpg"))); // Change the icon

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("        Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("        Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(cancelButton);
        panel.add(loginButton);
        getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        try {
            Scanner reader = new Scanner(LoginScreen.class.getResourceAsStream("credentials.txt"));
            String fileUsername = reader.nextLine();
            String filePassword = reader.nextLine();
            reader.close();
    
            if (username.equals(fileUsername) && password.equals(filePassword)) {
                System.out.println("Login successful!");
                dispose();
                ProfileScreen profileScreen = new ProfileScreen(username);
                profileScreen.setVisible(true);
            }
            else {
                System.out.println("Login failed!");
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to read credentials file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void main(String[] args) {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setVisible(true);
    }
}
