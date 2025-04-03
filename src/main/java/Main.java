public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.setMeasurements();

        ForecastDisplay forecastDisplay = new ForecastDisplay();
        forecastDisplay.getDaysAndMinAndMaxValuesTemperature();
    }
}
