import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.ArrayList;

public class ForecastDisplay implements Observer, DisplayElement {

    public ArrayList<String> getDaysAndMinAndMaxValuesTemperature() {
        ArrayList<String> listDaysAndMinMaxValuesTemperature = new ArrayList<>();
        String urlSiteWeather10days = "https://www.gismeteo.ru/weather-sochi-5233/10-days/";
        String pathToFileWithHtmlCode = "data/htmlSiteForWeather10days.html";
        try {
            Document document = Jsoup.connect(urlSiteWeather10days).get();
            String htmlCodeText = String.valueOf(document);
            FileWriter fileWriter = new FileWriter(pathToFileWithHtmlCode);
            fileWriter.write(htmlCodeText);

            System.out.println();
            //Для дней
            Elements elementsForDays = document.select(".day");
            String templateForDay = "<div class=\"day\">\n ";
            for (int i = 0; i < 10; i++) {
                Element currentElement = elementsForDays.get(i);
                String strCurrentElement = String.valueOf(currentElement);
                int leftIndexForDay = strCurrentElement.indexOf(templateForDay);
                if (leftIndexForDay != -1) {
                    leftIndexForDay += templateForDay.length();
                    int rightIndexForDay = strCurrentElement.indexOf("\n<", leftIndexForDay);
                    String day = strCurrentElement.substring(leftIndexForDay, rightIndexForDay);
                    System.out.println("Текущий день \"" + day + "\"");
                }
            }

            System.out.println();
            //Для минимальной температуры
            Elements elementsForMinTemperature = document.select(".mint");
            String templateForMinTemperature = "<div class=\"mint\">\n <temperature-value value=\"";
            for (int i = 0; i < 10; i++) {
                Element currentElement = elementsForMinTemperature.get(i);
                String strCurrentElement = String.valueOf(currentElement);
                int leftIndexForDay = strCurrentElement.indexOf(templateForMinTemperature);
                if (leftIndexForDay != -1) {
                    leftIndexForDay += templateForMinTemperature.length();
                    int rightIndexForDay = strCurrentElement.indexOf("\"", leftIndexForDay);
                    String minTemperature = strCurrentElement.substring(leftIndexForDay, rightIndexForDay);
                    System.out.println("Текущая минимальная температура \"" + minTemperature + "\"");
                }
            }

            System.out.println();
            //Для максимальной температуры
            Elements elementsForMaxTemperature = document.select(".maxt");
            String templateForMaxTemperature = "<div class=\"maxt\">\n <temperature-value value=\"";
            for (int i = 0; i < 10; i++) {
                Element currentElement = elementsForMaxTemperature.get(i);
                String strCurrentElement = String.valueOf(currentElement);
                int leftIndexForDay = strCurrentElement.indexOf(templateForMaxTemperature);
                if (leftIndexForDay != -1) {
                    leftIndexForDay += templateForMaxTemperature.length();
                    int rightIndexForDay = strCurrentElement.indexOf("\"", leftIndexForDay);
                    String maxTemperature = strCurrentElement.substring(leftIndexForDay, rightIndexForDay);
                    System.out.println("Текущая максимальная температура \"" + maxTemperature + "\"");
                }
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return listDaysAndMinMaxValuesTemperature;
    }

    @Override
    public void display() {
        System.out.println("Прогноз!");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

    }
}
