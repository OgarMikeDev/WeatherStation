import java.util.ArrayList;

public class StatisticsDisplay implements Observer, DisplayElement {
    private ForecastDisplay forecastDisplay;
    private double averageValueAllTemperatureAir;
    private ArrayList<Integer> listMinTemperaturesAir;
    private ArrayList<Integer> listMaxTemperaturesAir;
    private double minTemperatureAir;
    private double maxTemperatureAir;

    public StatisticsDisplay(ForecastDisplay forecastDisplay) {
        this.forecastDisplay = forecastDisplay;
        averageValueAllTemperatureAir = 0;
        listMinTemperaturesAir = forecastDisplay.getListMinTemperaturesAir();
        listMaxTemperaturesAir = forecastDisplay.getListMaxTemperaturesAir();
        minTemperatureAir = 0;
        maxTemperatureAir = 0;
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

    public void sortedAllTemperaturesAir(ArrayList<Integer> listMaxOrMinTemperaturesAir) {
        int i = 0;
        int j = listMaxOrMinTemperaturesAir.size() - 1;
        /*
        ВГАБ
        АБВГ
         */
        for (; i < listMaxOrMinTemperaturesAir.size() - 2; i++) {
            for (; j >= (i + 1); j--) {
                int temp = listMaxOrMinTemperaturesAir.get(i);
                if (listMaxOrMinTemperaturesAir.get(i).compareTo(listMaxOrMinTemperaturesAir.get(j)) > 0) {
                    listMaxOrMinTemperaturesAir.set(i, listMaxOrMinTemperaturesAir.get(j));
                    listMaxOrMinTemperaturesAir.set(j, temp
                    );
                }
            }
            j = listMaxOrMinTemperaturesAir.size() - 1;
        }
    }

    public double getMinTemperatureAir() {
        minTemperatureAir = listMinTemperaturesAir.get(0);
        return minTemperatureAir;
    }

    public double getMaxTemperatureAir() {
        maxTemperatureAir = listMaxTemperaturesAir.get(listMaxTemperaturesAir.size() - 1);
        return maxTemperatureAir;
    }

    @Override
    public void display() {
        System.out.println("\nСтатистика:");
        System.out.println("Ср. знач. всех температур: " + getAverageValueAllTemperatureAir());
        System.out.println("Минимальная температура: " + getMinTemperatureAir());
        sortedAllTemperaturesAir(listMaxTemperaturesAir);
        System.out.println("Максимальная температура: " + getMaxTemperatureAir());
    }

    @Override
    public void update() {
        getAverageValueAllTemperatureAir();
        sortedAllTemperaturesAir(listMinTemperaturesAir);
        sortedAllTemperaturesAir(listMaxTemperaturesAir);
    }
}
