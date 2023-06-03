public class j {
    public static void main(String[] args) {
        TemperatureHandler temperatureChecker = new TemperatureHandler();
        temperatureChecker.readDataFromCSV("temps.csv");

        String country = "Japan";
        String city = "Tokyo";
        String time = "Evening";
        double temperature = 30.5;

        String result = temperatureChecker.checkTemperature(country, city, time, temperature);
        System.out.println(result);
    }
}
