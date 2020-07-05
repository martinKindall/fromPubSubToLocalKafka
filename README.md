## Streaming data from Pub/Sub to Kafka

In this small project I use Java to consume data from Google Cloud  [new york taxi tycoon topic](https://github.com/googlecodelabs/cloud-dataflow-nyc-taxi-tycoon),
and send it to an on Premises __Kafka topic__, for further processing.

- __First__ one must setup a subscription on Google Cloud, the steps are explained on the link above.

- __Second__: configure Java to authenticate with your [Google Cloud Service Account](https://cloud.google.com/docs/authentication/getting-started)

- __Third__: configure Java to [produce messages to a Kafka topic](https://kafka.apache.org/10/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html)

- __Fourth__: configure the [Pub/Sub client](https://cloud.google.com/pubsub/docs/quickstart-client-libraries#pubsub-client-libraries-java)

- __Fifth__: Consume data with Pub/Sub and send it to Kafka. Congratz!  