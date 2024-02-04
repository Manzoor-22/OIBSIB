import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

class Question {
    private String question;
    private String answer;
    private String[] options;

    public Question(String question, String answer, String[] options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getOptions() {
        return options;
    }
}


public class ExamScreen extends JFrame{
    private int score = 0;
    private int currentQuestionIndex = 0;
    private JLabel timerLabel;
    private Timer timer;
    private List<Question> questions;

    public ExamScreen() {
        setTitle("Online Examination System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(ExamScreen.class.getResource("icon.jpg"))); // Change the icon

        JPanel timerPanel = new JPanel(new GridLayout(1, 2));
        timerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        timerLabel = new JLabel(String.valueOf("60"), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        timerPanel.add(timerLabel);

        JPanel questionPanel = new JPanel(new GridLayout(6, 1));
        questionPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel submitPanel = new JPanel(new GridLayout(1, 1));
        submitPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        questions = new ArrayList<>();
        questions.add(new Question("What is the chemical symbol for water?", "H2O", new String[]{"A) Wo", "B) H2O", "C) Wa", "D) H2"}));
        questions.add(new Question("Which planet is known as the \"Red Planet\"?", "Mars", new String[]{"A) Mars", "B) Jupiter", "C) Venus", "D) Saturn"}));
        questions.add(new Question("What is the largest ocean in the world?", "Pacific", new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}));
        questions.add(new Question("What is the capital city of France?", "Tokyo", new String[]{"A) Paris", "B) London", "C) Berlin", "D) Madrid"}));
        questions.add(new Question("What is the currency of Australia?", "Australian dollar", new String[]{"US dollar", "Euro", "Australian dollar", "Pound Sterling"}));

        Collections.shuffle(questions);

        updateQuestionPanel(questionPanel,submitPanel);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Your score is " + score + " out of " + questions.size(), "Exam completed", JOptionPane.INFORMATION_MESSAGE);
                endQuiz();
            }
        });
        submitPanel.add(submitButton);

        add(timerLabel, BorderLayout.NORTH);
        add(questionPanel, BorderLayout.CENTER);
        add(submitPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int remainingTime = Integer.parseInt(timerLabel.getText());
                if (remainingTime > 0) {
                    remainingTime--;
                    timerLabel.setText(String.valueOf(remainingTime));
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up! Your score is " + score + " out of " + questions.size(), "Time's up", JOptionPane.INFORMATION_MESSAGE);
                    endQuiz();
                }
            }
        });
        timer.start();
    }

    

private void updateQuestionPanel(JPanel questionPanel, JPanel submitPanel) {
    Question currentQuestion = questions.get(currentQuestionIndex);
    questionPanel.removeAll();

    JLabel questionLabel = new JLabel(currentQuestion.getQuestion());
    questionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    questionPanel.add(questionLabel);

    String[] options = currentQuestion.getOptions();
    for (int i = 0; i < options.length; i++) {
        JButton answerButton = new JButton(options[i]);
        answerButton.setFont(new Font("Arial", Font.PLAIN, 14));

        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerButton.getText().equals(currentQuestion.getAnswer())) {
                    score++;
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    updateQuestionPanel(questionPanel,submitPanel);
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Your score is " + score + " out of " + questions.size(), "Exam completed", JOptionPane.INFORMATION_MESSAGE);
                    endQuiz();
                }
            }
        });

        questionPanel.add(answerButton);
    }

    getContentPane().removeAll();
    getContentPane().add(timerLabel, BorderLayout.NORTH);
    getContentPane().add(questionPanel, BorderLayout.CENTER);
    getContentPane().add(submitPanel, BorderLayout.SOUTH);
    revalidate();
    repaint();
}
protected void endQuiz() {
    String username = null;
    try {
        Scanner reader = new Scanner(LoginScreen.class.getResourceAsStream("credentials.txt"));
        username = reader.nextLine();
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    ProfileScreen profileScreen = new ProfileScreen(username);
    profileScreen.setVisible(true);
    dispose();
}
}

