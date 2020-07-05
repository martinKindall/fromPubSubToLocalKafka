import com.google.cloud.pubsub.v1.Subscriber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

public class TaxiDataPubSubConsumer {

    private MyProducer producer;

    public TaxiDataPubSubConsumer(MyProducer producer) {
        this.producer = producer;
    }

    public void consumeDataAndSendToTopic(String projectId, String subscriptionId) {
        ProjectSubscriptionName subscriptionName =
                ProjectSubscriptionName.of(projectId, subscriptionId);

        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> {
                    producer.sendToTopic(message.getMessageId(), message.getData().toStringUtf8());
                    consumer.ack();
                };

        initSubscriptionAndReceiverForFixedTime(subscriptionName, receiver);
    }

    private void initSubscriptionAndReceiverForFixedTime(
            ProjectSubscriptionName subscriptionName,
            MessageReceiver receiver
    ) {
        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            subscriber.startAsync().awaitRunning();
            System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
            subscriber.awaitTerminated(7, TimeUnit.SECONDS);
        } catch (TimeoutException timeoutException) {
            subscriber.stopAsync();
        }
    }
}
