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

            Elements elements = document.select(".day");
            String templateForDay = "<div class=\"day\">\n ";
            for (Element currentElement : elements) {
                String strCurrentElement = String.valueOf(currentElement);
                int leftIndexForDay = strCurrentElement.indexOf(templateForDay);
                if (leftIndexForDay != -1) {
                    leftIndexForDay += templateForDay.length();
                    int rightIndexForDay = strCurrentElement.indexOf("\n<", leftIndexForDay);
                    String day = strCurrentElement.substring(leftIndexForDay, rightIndexForDay);
                    System.out.println("\"" + day + "\"");
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
