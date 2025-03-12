package com.mati.repositories.impl

import com.mati.dao.PaymentDao
import com.mati.db.PaymentsTable
import com.mati.db.PaymentsTable.paymentStatus
import com.mati.model.Payment
import com.mati.repositories.PaymentRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

class PaymentPostgresRepo : PaymentRepository {

    override suspend fun createPayment(paymentDate: LocalDateTime?, orderId: Int, paymentMethod: String, paymentStatus: String): Payment? {
        return newSuspendedTransaction(Dispatchers.IO) {
            PaymentDao.new {
                this.paymentDate = paymentDate
                this.orderId = orderId
                this.paymentMethod = paymentMethod
                this.paymentstatus = paymentStatus
            }.toModel()
        }
    }


    override suspend fun getPaymentById(paymentId: Int): Payment? {
        return newSuspendedTransaction(Dispatchers.IO) {
            PaymentDao.findById(paymentId)?.toModel()
        }
    }

    override suspend fun getPaymentsByOrderId(orderId: Int): List<Payment> {
        return newSuspendedTransaction(Dispatchers.IO) {
            PaymentDao.find { PaymentsTable.orderId eq orderId }
                .map { it.toModel() }
        }
    }

    override suspend fun updatePaymentStatus(paymentId: Int, newStatus: String): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            PaymentDao.findById(paymentId)?.let {
                it.paymentstatus = newStatus
                true
            } ?: false
        }
    }

    override suspend fun deletePayment(paymentId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            PaymentDao.findById(paymentId)?.let {
                it.delete()
                true
            } ?: false
        }
    }

    private fun PaymentDao.toModel() = Payment(
        id.value,
        paymentDate,
        orderId,
        paymentMethod,
        paymentstatus
    )
}
