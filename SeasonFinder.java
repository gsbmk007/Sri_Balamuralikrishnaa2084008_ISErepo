import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class SeasonFinder {
    public static void main(String[] args) {

        // Read the seasons data from the file
        Map<String, String[]> seasonsData = seasonhandler.readSeasonsDataFromFile("Seasons.csv");
        TemperatureHandler.readTemperatureDataFromFile("temps.csv");

        // Create the main frame
        JFrame frame = new JFrame("Season Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Set the background color of the frame to dark grey (bg-gray-800)
        frame.getContentPane().setBackground(new Color(51, 51, 51));
        frame.getContentPane().setLayout(new BorderLayout());

        // Create the panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(new Color(51, 51, 51));

        // Create the UI components
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryTextField = new JTextField();

        JLabel monthLabel = new JLabel("Month (1-12):");
        JTextField monthTextField = new JTextField();

        JLabel cityLabel = new JLabel("City name:");
        JTextField cityTextField = new JTextField();

        JLabel temperatureLabel = new JLabel("Temperature reading:");
        JTextField temperatureTextField = new JTextField();

        countryLabel.setForeground(Color.WHITE);
        monthLabel.setForeground(Color.WHITE);
        cityLabel.setForeground(Color.WHITE);
        temperatureLabel.setForeground(Color.WHITE);

        formPanel.add(countryLabel);
        formPanel.add(countryTextField);
        formPanel.add(monthLabel);
        formPanel.add(monthTextField);
        formPanel.add(cityLabel);
        formPanel.add(cityTextField);
        formPanel.add(temperatureLabel);
        formPanel.add(temperatureTextField);

        // Create the submit button
        JButton submitButton = new JButton("Submit");

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

        // Create a panel for the submit button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(51, 51, 51));
        buttonPanel.add(submitButton);

        // Add the form panel and button panel to the frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame resizable
        frame.setResizable(true);

        // Make the frame visible
        frame.setVisible(true);
    }
}
