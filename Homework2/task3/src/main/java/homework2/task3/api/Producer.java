package homework2.task3.api;

public interface Producer {

    void newMessage(String topic, String message);

    void subscribe(Consumer consumer);

    void unsubscribe(Consumer consumer);
}
