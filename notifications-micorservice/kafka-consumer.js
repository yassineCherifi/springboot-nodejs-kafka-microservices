const environment = process.env;

const kafka = require('kafka-node'),
    Consumer = kafka.Consumer,
    client = new kafka.KafkaClient(environment.KAFKA_HOST);


consumer = new Consumer(
    client,
    [
        {topic: 'topic1', partition: 0, offset: 0}
    ],
    {fromOffset: true}
);

consumer.on('message', function (message) {
    console.log(message);
});

consumer.on('error', function (err) {
    console.log('ERROR: ' + err.toString());
});