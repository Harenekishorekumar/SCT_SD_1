import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromScale;
    private JComboBox<String> toScale;
    private JLabel resultLabel;

    public TemperatureConverter() {
        // Set up the window
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Input field
        JLabel inputLabel = new JLabel("Enter Temperature:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(inputLabel, gbc);

        inputField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(inputField, gbc);

        // From scale dropdown
        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);

        String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};
        fromScale = new JComboBox<>(scales);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fromScale, gbc);

        // To scale dropdown
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);

        toScale = new JComboBox<>(scales);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(toScale, gbc);

        // Convert button
        JButton convertButton = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(convertButton, gbc);

        // Result label
        resultLabel = new JLabel("Result: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        // Action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double inputTemp = Double.parseDouble(inputField.getText());
            String from = (String) fromScale.getSelectedItem();
            String to = (String) toScale.getSelectedItem();
            double result = 0;

            // Conversion logic
            if (from.equals("Celsius")) {
                if (to.equals("Fahrenheit")) {
                    result = (inputTemp * 9/5) + 32;
                } else if (to.equals("Kelvin")) {
                    result = inputTemp + 273.15;
                } else {
                    result = inputTemp; // Celsius to Celsius
                }
            } else if (from.equals("Fahrenheit")) {
                if (to.equals("Celsius")) {
                    result = (inputTemp - 32) * 5/9;
                } else if (to.equals("Kelvin")) {
                    result = (inputTemp - 32) * 5/9 + 273.15;
                } else {
                    result = inputTemp; // Fahrenheit to Fahrenheit
                }
            } else if (from.equals("Kelvin")) {
                if (to.equals("Celsius")) {
                    result = inputTemp - 273.15;
                } else if (to.equals("Fahrenheit")) {
                    result = (inputTemp - 273.15) * 9/5 + 32;
                } else {
                    result = inputTemp; // Kelvin to Kelvin
                }
            }

            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverter converter = new TemperatureConverter();
            converter.setVisible(true);
        });
    }
}
