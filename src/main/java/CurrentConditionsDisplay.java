public class CurrentConditionsDisplay implements Observer, DisplayElement {


    @Override
    public void display() {
        System.out.println("Текущее состояние!");
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

    }
}
