package it.polito.wa2.g12.paymentservice.service.impl

import it.polito.wa2.g12.paymentservice.dto.TransactionDTO
import it.polito.wa2.g12.paymentservice.entity.Transaction
import it.polito.wa2.g12.paymentservice.entity.toDTO
import it.polito.wa2.g12.paymentservice.repository.TransactionRepository
import it.polito.wa2.g12.paymentservice.service.PaymentService
import it.polito.wa2.g12.ticketcatalogueservice.kafka.BillingMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Service
class PaymentServiceImpl: PaymentService {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    override fun getAllTransactions(): Flow<TransactionDTO> {
        return transactionRepository.findAllTransactions().map { it.toDTO() }
    }

    override fun getAllUserTransactions(username: String): Flow<TransactionDTO> {
        return transactionRepository.findAllUserTransactions(username).map { it.toDTO() }
    }
}