import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    //Температура
    private float temperature;
    //Влажность
    private float humidity;
    //Давление
    private float pressure;

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements() {
        String urlSiteWeather = "https://www.gismeteo.ru/weather-sochi-5233/now/";
        String pathToFileWithHtmlCode = "data/htmlSiteForWeather.html";
        try {
            Document document = Jsoup.connect(urlSiteWeather).get();
            String htmlCodeText = String.valueOf(document);
            FileWriter fileWriter = new FileWriter(pathToFileWithHtmlCode);
            fileWriter.write(htmlCodeText);


            Elements partWithWeatherData = document.select(".now-info");
            System.out.println("Path weather: \n\"" + String.valueOf(partWithWeatherData) + "\"\n");
        } catch (Exception ex) {
            ex.getMessage();
        }
        measurementsChanged();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int indexObserverInList = observers.indexOf(observer);
        if (indexObserverInList >= 0) {
            observers.remove(indexObserverInList);
        }
    }

    @Override
    public void notifyObservers() {
        for (int indexCurrentObserver = 0; indexCurrentObserver < observers.size(); indexCurrentObserver++) {
            Observer observer = observers.get(indexCurrentObserver);
            observer.update(temperature, humidity, pressure);
        }
    }
}
