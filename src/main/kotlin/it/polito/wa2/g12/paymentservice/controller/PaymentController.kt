package it.polito.wa2.g12.paymentservice.controller

import it.polito.wa2.g12.paymentservice.service.impl.PaymentServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(val paymentService: PaymentServiceImpl) {

    @GetMapping("/transactions")
    fun getAllCustomers() : ResponseEntity<Any> {
        val res = paymentService.getTransactions()
        return ResponseEntity(res, HttpStatus.OK)
    }
}