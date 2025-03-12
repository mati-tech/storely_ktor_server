package com.mati.repositories

import com.mati.model.Payment
import java.time.LocalDateTime

interface PaymentRepository {

    suspend fun createPayment(paymentDate: LocalDateTime?, orderId: Int, paymentMethod: String, paymentStatus: String): Payment?
    suspend fun getPaymentById(paymentId: Int): Payment?
    suspend fun getPaymentsByOrderId(orderId: Int): List<Payment>
    suspend fun updatePaymentStatus(paymentId: Int, newStatus: String): Boolean
    suspend fun deletePayment(paymentId: Int): Boolean

}
