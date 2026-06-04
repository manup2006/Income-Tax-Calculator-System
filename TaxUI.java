import java.awt.*;
import javax.swing.*;

class TaxCalculator {

    public static String getSlab(int age) {
        if (age <= 60) return "Below 60";
        else if (age <= 80) return "60 to 80";
        else return "Above 80";
    }

    public static double calculateTax(int age, double salary) {
        if (age <= 60) return slabBelow60(salary);
        else if (age <= 80) return slab60to80(salary);
        else return slabAbove80(salary);
    }

    private static double slabBelow60(double salary) {
        double tax = 0;

        if (salary <= 250000) return 0;
        else if (salary <= 500000)
            tax = (salary - 250000) * 0.05;
        else if (salary <= 1000000)
            tax = 250000 * 0.05 + (salary - 500000) * 0.2;
        else
            tax = 250000 * 0.05 + 500000 * 0.2 + (salary - 1000000) * 0.3;

        return tax * 1.04;
    }

    private static double slab60to80(double salary) {
        double tax = 0;

        if (salary <= 300000) return 0;
        else if (salary <= 500000)
            tax = (salary - 300000) * 0.05;
        else if (salary <= 1000000)
            tax = 200000 * 0.05 + (salary - 500000) * 0.2;
        else
            tax = 200000 * 0.05 + 500000 * 0.2 + (salary - 1000000) * 0.3;

        return tax * 1.04;
    }

    private static double slabAbove80(double salary) {
        double tax = 0;

        if (salary <= 500000) return 0;
        else if (salary <= 1000000)
            tax = (salary - 500000) * 0.2;
        else
            tax = 500000 * 0.2 + (salary - 1000000) * 0.3;

        return tax * 1.04;
    }
}

public class TaxUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Income Tax Calculator");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        
        JTextField nameField = new JTextField();
        JTextField aadharField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField ageField = new JTextField();
        JTextField salaryField = new JTextField();

     
        addRow(panel, gbc, 0, "Name:", nameField);
        addRow(panel, gbc, 1, "Aadhar:", aadharField);
        addRow(panel, gbc, 2, "Gender:", genderBox);
        addRow(panel, gbc, 3, "Age:", ageField);
        addRow(panel, gbc, 4, "Salary:", salaryField);

        JButton calcButton = new JButton("Calculate Tax");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(calcButton, gbc);

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resultArea.setBackground(new Color(255, 255, 240));

        gbc.gridy = 6;
        panel.add(resultArea, gbc);

        calcButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String aadhar = aadharField.getText();
                String gender = genderBox.getSelectedItem().toString();
                int age = Integer.parseInt(ageField.getText());
                double salary = Double.parseDouble(salaryField.getText());

                double tax = TaxCalculator.calculateTax(age, salary);
                String slab = TaxCalculator.getSlab(age);

                resultArea.setText(
                        "Name: " + name + "\n" +
                        "Aadhar: " + aadhar + "\n" +
                        "Gender: " + gender + "\n" +
                        "Age: " + age + "\n" +
                        "Salary: " + salary + "\n\n" +
                        "Slab: " + slab + "\n" +
                        "Income Tax: " + String.format("%.2f", tax)
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid details.");
            }
        });

        frame.setVisible(true);
    }

    private static void addRow(JPanel panel, GridBagConstraints gbc, int y, String label, Component field) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
        gbc.weightx = 0;
    }
}