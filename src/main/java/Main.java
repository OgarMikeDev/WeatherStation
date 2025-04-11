public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        weatherData.setMeasurements();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        currentConditionsDisplay.update(
                weatherData.getTemperature(),
                weatherData.getHumidity(),
                weatherData.getPressure()
        );
        currentConditionsDisplay.display();

        ForecastDisplay forecastDisplay = new ForecastDisplay();
        forecastDisplay.getDaysAndMinMaxValuesTemperature();
        forecastDisplay.display();

        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(forecastDisplay);
        statisticsDisplay.getAverageValueAllTemperatureAir();
        statisticsDisplay.display();
    }
}
