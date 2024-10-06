import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator extends JFrame {
    private JTextField weightField;
    private JTextField feetField;
    private JTextField inchesField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JPanel inputPanel, resultPanel;

    public BMICalculator() {
        setTitle("BMI Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0x2A2D34));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        
        JLabel weightLabel = new JLabel("Weight (kg): ");
        weightLabel.setForeground(Color.WHITE);
        weightField = new JTextField();
        
        JLabel feetLabel = new JLabel("Height (ft): ");
        feetLabel.setForeground(Color.WHITE);
        feetField = new JTextField();
        
        JLabel inchesLabel = new JLabel("Height (in): ");
        inchesLabel.setForeground(Color.WHITE);
        inchesField = new JTextField();
        
        calculateButton = new JButton("Calculate BMI");
        calculateButton.setBackground(new Color(0x4CAF50));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        
        resultLabel = new JLabel("");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(feetLabel);
        inputPanel.add(feetField);
        inputPanel.add(inchesLabel);
        inputPanel.add(inchesField);
        inputPanel.add(new JLabel());
        inputPanel.add(calculateButton);

        
        resultPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0x4CAF50));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBackground(new Color(0x2A2D34));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Your BMI Result:");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        resultPanel.add(titleLabel);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createVerticalStrut(10));

       
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double feet = Double.parseDouble(feetField.getText());
            double inches = Double.parseDouble(inchesField.getText());

            double totalInches = (feet * 12) + inches;
            double heightInMeters = totalInches * 0.0254;

            double bmi = weight / (heightInMeters * heightInMeters);
            String healthStatus = "";

            if (bmi < 18.5) {
                healthStatus = "Underweight";
            } else if (bmi < 24.9) {
                healthStatus = "Normal weight";
            } else if (bmi < 29.9) {
                healthStatus = "Overweight";
            } else {
                healthStatus = "Obesity";
            }

            resultLabel.setText(String.format("<html><span style='color: white; font-size: 24px;'>BMI: <strong>%.2f</strong> - %s</span></html>", bmi, healthStatus));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BMICalculator calculator = new BMICalculator();
            calculator.setVisible(true);
        });
    }
}
