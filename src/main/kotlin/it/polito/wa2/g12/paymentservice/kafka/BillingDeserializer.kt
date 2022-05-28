package it.polito.wa2.g12.paymentservice.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import it.polito.wa2.g12.ticketcatalogueservice.kafka.Billing
import it.polito.wa2.g12.ticketcatalogueservice.kafka.Transaction
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer

class BillingDeserializer : Deserializer<Billing>{
    private val objectMapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): Billing? {
        return objectMapper.readValue(
            String(
                data ?: throw SerializationException("Error when deserializing byte[] to Product"), Charsets.UTF_8
            ), Billing::class.java
        )
    }

    override fun close() {}
}