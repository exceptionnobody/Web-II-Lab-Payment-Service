package it.polito.wa2.g12.paymentservice.repository

import it.polito.wa2.g12.paymentservice.entity.Transaction
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TransactionRepository: ReactiveCrudRepository<Transaction?, Long?>