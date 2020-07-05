import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerExample implements MyProducer{

    private String topicName;
    private Producer<String, String> producer;

    public ProducerExample(String topicName) {
        config();
        this.topicName = topicName;
    }

    void config() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    @Override
    public void sendToTopic(String id, String message) {
        producer.send(new ProducerRecord<String, String>(topicName, id, message));
    }

    @Override
    public void close() {
        producer.close();
    }
}
