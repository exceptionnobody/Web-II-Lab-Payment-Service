package it.polito.wa2.g12.paymentservice.service

import it.polito.wa2.g12.paymentservice.entity.Transaction
import reactor.core.publisher.Flux

interface PaymentService {
    fun getTransactions(): Flux<Transaction?>
}