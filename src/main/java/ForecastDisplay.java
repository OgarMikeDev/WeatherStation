public class ForecastDisplay implements Observer, DisplayElement {


    @Override
    public void display() {
        System.out.println("Прогноз!");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

    }
}
