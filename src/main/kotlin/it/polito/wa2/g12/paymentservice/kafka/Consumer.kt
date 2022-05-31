package it.polito.wa2.g12.paymentservice.kafka

import it.polito.wa2.g12.paymentservice.entity.Transaction
import it.polito.wa2.g12.paymentservice.service.impl.PaymentServiceImpl
import it.polito.wa2.g12.ticketcatalogueservice.kafka.BillingMessage
import it.polito.wa2.g12.ticketcatalogueservice.kafka.TransactionMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class Consumer {
    @Value("\${kafka.topics.transaction}")
    lateinit var  topicT: String
    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Any>
    @Autowired
    lateinit var paymentServiceImpl: PaymentServiceImpl

    @KafkaListener(topics = ["\${kafka.topics.payment}"], groupId = "ppr")
    fun paymentListener(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
        val billingMessage: BillingMessage = consumerRecord.value() as BillingMessage
        val mono =paymentServiceImpl.saveTransaction(billingMessage)
        val defaultTransaction = Transaction(0,"", BigDecimal.ZERO, LocalDateTime.MIN)
        mono.defaultIfEmpty(defaultTransaction).subscribe {
            if (it!!.id != null) {
                val message: Message<TransactionMessage> = MessageBuilder
                    .withPayload(TransactionMessage(it.orderId, it.id, "CONFIRMED", it.username))
                    .setHeader(KafkaHeaders.TOPIC, topicT)
                    .setHeader("X-Custom-Header", "Custom header here")
                    .build()
                println(message)
                kafkaTemplate.send(message)
                ack.acknowledge()
            }
            else
            {
                val message: Message<TransactionMessage> = MessageBuilder
                    .withPayload(TransactionMessage(billingMessage.order_id, null, "FAILURE", billingMessage.username))
                    .setHeader(KafkaHeaders.TOPIC, topicT)
                    .setHeader("X-Custom-Header", "Custom header here")
                    .build()
                println(message)
                kafkaTemplate.send(message)
                ack.acknowledge()
            }
        }
    }
}