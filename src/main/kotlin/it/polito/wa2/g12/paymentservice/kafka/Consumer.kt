package it.polito.wa2.g12.paymentservice.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class Consumer {

    @KafkaListener(topics = ["\${kafka.topics.payment}"], groupId = "ppr")
    fun paymentListener(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
        println("Message received $consumerRecord")
        ack.acknowledge()
    }
}