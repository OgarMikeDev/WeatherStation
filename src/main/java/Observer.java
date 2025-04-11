public interface Observer {
    default void update(float temperature, float humidity, float pressure) {

    }

    default void update() {

    }
}
