import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ProfileScreen extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel welcomeLabel;
    private JButton startExamButton;
    private JButton logoutButton;
    private JButton changeCredentialsButton;

    public ProfileScreen(String username) {
        setTitle("Online Examination System - " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ProfileScreen.class.getResource("icon.jpg"))); // Change the icon

        JPanel welcomePanel = new JPanel(new GridLayout(1, 1));
        welcomePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomePanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        startExamButton = new JButton("Start Exam");
        startExamButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startExamButton.addActionListener(this);
        buttonPanel.add(startExamButton);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.addActionListener(this);
        buttonPanel.add(logoutButton);

        changeCredentialsButton = new JButton("Change Username/Password");
        changeCredentialsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        changeCredentialsButton.addActionListener(this);
        buttonPanel.add(changeCredentialsButton);

        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startExamButton) {
            ExamScreen examScreen = new ExamScreen();
            examScreen.setVisible(true);
            dispose();
        } else if (e.getSource() == logoutButton) {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            dispose();
        } else if (e.getSource() == changeCredentialsButton) {
            ChangeCredentialsScreen changeCredentialsScreen = new ChangeCredentialsScreen();
            changeCredentialsScreen.setVisible(true);
        }
    }
}

