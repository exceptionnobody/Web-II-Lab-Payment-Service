package it.polito.wa2.g12.paymentservice.kafka

import it.polito.wa2.g12.ticketcatalogueservice.kafka.Transaction
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer

class TransactionSerializer : Serializer<Transaction> {
    private val objectMapper = ObjectMapper()

    override fun serialize(topic: String?, data: Transaction?): ByteArray? {
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Product to ByteArray[]")
        )
    }

    override fun close() {}
}
