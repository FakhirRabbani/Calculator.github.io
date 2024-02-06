import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;

    private double num1, num2, result;
    private String operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        textField = new JTextField();
        textField.setEditable(false);

        // Set font size to 24 and make it bold
        textField.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(textField);

        String[] buttonLabels = {
                "1", "2", "3",
                "4", "5", "6", 
                "7", "8", "9", 
                "0", ".", "=", 
                "+","-","*","/",
                "log", "sin", "cos", "tan",
                "log-1", "sin-1", "cos-1", "tan-1",
                "Del" // Delete button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            // Set font size to 24 and make it bold
            button.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "=":
                    calculate();
                    break;
                case "/":
                case "*":
                case "-":
                case "+":
                case "log":
                case "sin":
                case "cos":
                case "tan":
                case "log-1":
                case "sin-1":
                case "cos-1":
                case "tan-1":
                    operator = buttonText;
                    if (!operator.startsWith("log") && !operator.endsWith("-1")) {
                        num1 = Double.parseDouble(textField.getText());
                        textField.setText("");
                    }
                    break;
                case "Del": // Delete button
                    String currentText = textField.getText();
                    if (!currentText.isEmpty()) {
                        textField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    return; // Do not proceed further for delete button
                default:
                    textField.setText(textField.getText() + buttonText);
            }
        }
    }

    private void calculate() {
        num2 = Double.parseDouble(textField.getText());

        switch (operator) {
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "+":
                result = num1 + num2;
                break;
            case "log":
                result = Math.log10(num2);
                break;
            case "sin":
                result = Math.sin(Math.toRadians(num2));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(num2));
                break;
            case "tan":
                result = Math.tan(Math.toRadians(num2));
                break;
            case "log-1":
                result = Math.pow(10, num2);
                break;
            case "sin-1":
                result = Math.toDegrees(Math.asin(num2));
                break;
            case "cos-1":
                result = Math.toDegrees(Math.acos(num2));
                break;
            case "tan-1":
                result = Math.toDegrees(Math.atan(num2));
                break;
        }

        textField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
