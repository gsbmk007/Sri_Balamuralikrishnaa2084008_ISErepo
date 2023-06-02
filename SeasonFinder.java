import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

public class SeasonFinder {
    public static void main(String[] args) {

        // Read the seasons data from the file
        Map<String, String[]> seasonsData = seasonhandler.readSeasonsDataFromFile("Seasons.csv");
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");

        // Create the main frame
        JFrame frame = new JFrame("Season Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        // Create the UI components
        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setBounds(20, 20, 120, 25);
        frame.add(countryLabel);

        JTextField countryTextField = new JTextField();
        countryTextField.setBounds(150, 20, 200, 25);
        frame.add(countryTextField);

        JLabel monthLabel = new JLabel("Month 12");
        monthLabel.setBounds(20, 50, 200, 25);
        frame.add(monthLabel);

        JTextField monthTextField = new JTextField();
        monthTextField.setBounds(230, 50, 120, 25);
        frame.add(monthTextField);

        JLabel cityLabel = new JLabel("City name:");
        cityLabel.setBounds(230, 80, 120, 25);
        frame.add(cityLabel);

        JTextField cityTextField = new JTextField();
        cityTextField.setBounds(150, 80, 200, 25);
        frame.add(cityTextField);

        JLabel temperatureLabel = new JLabel("Temperature reading:");
        temperatureLabel.setBounds(20, 110, 200, 25);
        frame.add(temperatureLabel);

        JTextField temperatureTextField = new JTextField();
        temperatureTextField.setBounds(30, 110, 120, 25);
        frame.add(temperatureTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 140, 100, 25);
        frame.add(submitButton);

        // Add action listener to the submit button
        submitButton.addActionListener(e -> {
            String country = countryTextField.getText();
            int month = Integer.parseInt(monthTextField.getText());
            String city = cityTextField.getText();
            double temperature = Double.parseDouble(temperatureTextField.getText());

            // Find and display the corresponding season
            String season = seasonhandler.getSeason(seasonsData, country, month);
            TemperatureHandler.compareTemperature(country, city, temperature);

            if (season != null) {
                JOptionPane.showMessageDialog(frame, "The season in " + country + " for month " + month + " is: " + season);
            } else {
                JOptionPane.showMessageDialog(frame, "Season data not found for the given country and month.");
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
