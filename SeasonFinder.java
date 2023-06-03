import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Map;

public class SeasonFinder {
    public static void main(String[] args) {

        // Read the seasons data from the file
        Map<String, String[]> seasonsData = SeasonHandler.readSeasonsDataFromFile("Seasons.csv");
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");

        // Create the main frame
        JFrame frame = new JFrame("Season Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        // Set the background color of the frame to dark grey (bg-gray-800)
        frame.getContentPane().setBackground(new Color(51, 51, 51));
        frame.getContentPane().setLayout(new BorderLayout());

        // Create the panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(new Color(51, 51, 51));

        // Create a line border with grey color
        Border border = BorderFactory.createLineBorder(Color.GRAY);

        // Create the UI components
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setForeground(Color.WHITE);
        JTextField countryTextField = new JTextField();
        countryTextField.setBorder(border);

        JLabel monthLabel = new JLabel("Month (1-12):");
        monthLabel.setForeground(Color.WHITE);
        JTextField monthTextField = new JTextField();
        monthTextField.setBorder(border);

        JLabel cityLabel = new JLabel("City name:");
        cityLabel.setForeground(Color.WHITE);

        JTextField cityTextField = new JTextField();
        cityTextField.setBorder(border);

        JLabel temperatureLabel = new JLabel("Temperature reading:");
        
        JTextField temperatureTextField = new JTextField();
        temperatureTextField.setBorder(border);
        temperatureLabel.setForeground(Color.WHITE);
        JLabel resultLabel = new JLabel("Season:");
        resultLabel.setForeground(Color.WHITE);
        JLabel temperatureResultLabel = new JLabel("");
        temperatureResultLabel.setForeground(Color.WHITE);

        JLabel resultTextLabel = new JLabel("Result:");
        resultTextLabel.setForeground(Color.WHITE);
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setForeground(Color.WHITE);
        resultTextArea.setBackground(new Color(51, 51, 51));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        formPanel.add(countryLabel);
        formPanel.add(countryTextField);
        formPanel.add(monthLabel);
        formPanel.add(monthTextField);
        formPanel.add(cityLabel);
        formPanel.add(cityTextField);
        formPanel.add(temperatureLabel);
        formPanel.add(temperatureTextField);
        formPanel.add(resultLabel);
        formPanel.add(temperatureResultLabel);
        formPanel.add(resultTextLabel);
        formPanel.add(scrollPane);

        // Create the submit button
        JButton submitButton = new JButton("Submit");

        // Add action listener to the submit button
        submitButton.addActionListener(e -> {
            String country = countryTextField.getText();
            int month = Integer.parseInt(monthTextField.getText());
            String city = cityTextField.getText();
            double temperature = Double.parseDouble(temperatureTextField.getText());

            // Find and display the corresponding season
            String season = SeasonHandler.getSeason(seasonsData, country, month);
            String result = TemperatureHandler.compareTemperature(country, city, temperature);
            System.out.println(result);

            if (season != null) {
                temperatureResultLabel.setText("The season in " + country + " for month " + month + " is: " + season);
            } else {
                temperatureResultLabel.setText("Season data not found for the given country and month.");
            }

            resultTextArea.setText(result);
        });

        // Create a panel for the submit button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 51, 51));
        buttonPanel.add(submitButton);

        // Add the form panel and button panel to the frame
        scrollPane.setPreferredSize(new Dimension(3000, 1050)); // Adjust the width and height as needed

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame resizable
        frame.setResizable(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
