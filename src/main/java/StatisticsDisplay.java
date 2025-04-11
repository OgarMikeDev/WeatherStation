import java.util.ArrayList;

public class StatisticsDisplay implements Observer, DisplayElement {
    private ForecastDisplay forecastDisplay;
    private double averageValueAllTemperatureAir;
    private ArrayList<Double> listAllSortedTemperaturesAir;

    public StatisticsDisplay(ForecastDisplay forecastDisplay) {
        this.forecastDisplay = forecastDisplay;
        averageValueAllTemperatureAir = 0;
        listAllSortedTemperaturesAir = new ArrayList<>();
    }

    public double getAverageValueAllTemperatureAir() {
        double sum = 0;
        for (double currentAverageTemperatureAir : forecastDisplay.getListAllTemperaturesAir()) {
            sum += currentAverageTemperatureAir;
        }
        int countTemperaturesAir = forecastDisplay.getListAllTemperaturesAir().size();
        averageValueAllTemperatureAir = sum / countTemperaturesAir;
        return averageValueAllTemperatureAir;
    }

    public void sortedAllTemperaturesAir() {
        listAllSortedTemperaturesAir = forecastDisplay.getListAllTemperaturesAir();
        int i = 0;
        int j = listAllSortedTemperaturesAir.size() - 1;
        /*
        ВГАБ
        АБВГ
         */
        for (; i < listAllSortedTemperaturesAir.size() - 2; i++) {
            for (; j >= (i + 1); j--) {
                double temp = listAllSortedTemperaturesAir.get(i);
                if (listAllSortedTemperaturesAir.get(i).compareTo(listAllSortedTemperaturesAir.get(j)) > 0) {
                    listAllSortedTemperaturesAir.set(i, listAllSortedTemperaturesAir.get(j));
                    listAllSortedTemperaturesAir.set(j, temp
                    );
                }
            }
            j = forecastDisplay.getListAllTemperaturesAir().size() - 1;
        }
        System.out.println("Сорт. список всех темп." + listAllSortedTemperaturesAir);
    }

    @Override
    public void display() {
        System.out.println("\nСтатистика!");
        System.out.println("Ср. знач. всех температур: " + averageValueAllTemperatureAir);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

    }
}
