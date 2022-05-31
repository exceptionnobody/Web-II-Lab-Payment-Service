package it.polito.wa2.g12.paymentservice.controller

import it.polito.wa2.g12.paymentservice.dto.TransactionDTO
import it.polito.wa2.g12.paymentservice.service.impl.PaymentServiceImpl
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(val paymentService: PaymentServiceImpl) {

    @GetMapping("/transactions")
    fun getAllTransactions() : Flow<TransactionDTO> {
        // TODO: extract username from the principal
        return paymentService.getAllUserTransactions("MarioRossi")
    }

    @GetMapping("/admin/transactions")
    fun getUserTransactions() : Flow<TransactionDTO> {
        return paymentService.getAllTransactions()
    }
}