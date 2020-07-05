public interface MyProducer {
    void sendToTopic(String id, String message);
    void close();
}
