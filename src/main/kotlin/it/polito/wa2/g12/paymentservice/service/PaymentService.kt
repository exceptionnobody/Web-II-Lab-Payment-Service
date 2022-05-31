package it.polito.wa2.g12.paymentservice.service

import it.polito.wa2.g12.paymentservice.dto.TransactionDTO
import it.polito.wa2.g12.paymentservice.entity.Transaction
import it.polito.wa2.g12.ticketcatalogueservice.kafka.BillingMessage
import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Mono

interface PaymentService {
    fun getAllTransactions(): Flow<TransactionDTO>
    fun getAllUserTransactions(username: String): Flow<TransactionDTO>
    fun saveTransaction(bill: BillingMessage): Mono<Transaction?>
}