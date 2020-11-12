const express = require('express');
const router = express.Router();
const kafka = require('kafka-node');
const environment = process.env;

/* GET home page. */
router.get('/kafka/publish', function (req, res, next) {
    console.log("message sent");
    try {
        const Producer = kafka.Producer;
        const client = new kafka.KafkaClient(environment.KAFKA_HOST);
        const producer = new Producer(client);
        const kafka_topic = 'example';
        console.log(kafka_topic);
        let payloads = [
            {
                topic: "topic1",
                messages: "message from nodejs"
            }
        ];

        producer.on('ready', async function () {
            let push_status = producer.send(payloads, (err, data) => {
                if (err) {
                    console.log('[kafka-producer -> ' + kafka_topic + ']: broker update failed');
                } else {
                    console.log('[kafka-producer -> ' + kafka_topic + ']: broker update success');
                }
            });
            res.json({success:"message sent from nodejs"});
        });

        producer.on('error', function (err) {
            console.log(err);
            console.log('[kafka-producer -> ' + kafka_topic + ']: connection errored');
            throw err;
        });
    } catch (e) {
        console.log(e);
    }
});

module.exports = router;
