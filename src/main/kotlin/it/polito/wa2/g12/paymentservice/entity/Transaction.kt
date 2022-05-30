package it.polito.wa2.g12.paymentservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("transactions")
class Transaction(
    @Column
    var orderId: Int,
    @Column
    var username: String,
    @Column
    var amount: BigDecimal,
    @Column
    var issuedAt: LocalDateTime
) {
    @Id
    var id: Long? = null
}