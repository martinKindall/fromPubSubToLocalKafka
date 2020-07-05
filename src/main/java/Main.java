public class Main {
    public static void main(String... args) throws Exception {
        String projectId = "qwiklabs-gcp-02-088571c83461";
        String subscriptionId = "taxi-test-sub";
        TaxiDataPubSubConsumer pubSubConsumer = new TaxiDataPubSubConsumer(new ProducerExample("test"));
        pubSubConsumer.consumeDataAndSendToTopic(projectId, subscriptionId);
    }
}
