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

    override fun saveTransaction(bill: BillingMessage): Mono<Transaction?> {
        //check credit card infos
        val format = SimpleDateFormat("MM/yy")
        val exp = format.parse(bill.exp)
        if((bill.ccn.length == 16 || bill.ccn.length == 15) && bill.card_holder.isNotEmpty() && bill.cvv.length==3 && exp.after(Date())) {
            return mono { transactionRepository.save(Transaction(bill.order_id,bill.username,bill.price, LocalDateTime.now()))}
        }
        else
        {
           return Mono.empty()
        }
    }

    override fun getAllTransactions(): Flow<TransactionDTO> {
        return transactionRepository.findAllTransactions().map { it.toDTO() }
    }

    override fun getAllUserTransactions(username: String): Flow<TransactionDTO> {
        return transactionRepository.findAllUserTransactions(username).map { it.toDTO() }
    }
}