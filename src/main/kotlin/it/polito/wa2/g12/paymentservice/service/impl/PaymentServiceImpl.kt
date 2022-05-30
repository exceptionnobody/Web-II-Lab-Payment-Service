package it.polito.wa2.g12.paymentservice.service.impl

import it.polito.wa2.g12.paymentservice.entity.Transaction
import it.polito.wa2.g12.paymentservice.repository.TransactionRepository
import it.polito.wa2.g12.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PaymentServiceImpl: PaymentService {
    @Autowired
    lateinit var transactionRepository: TransactionRepository

    override fun getTransactions(): Flux<Transaction?> {
        return transactionRepository.findAll()
    }
}