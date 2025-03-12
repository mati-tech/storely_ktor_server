package com.mati.repositories.impl


import com.mati.dao.OrderDao
import com.mati.db.OrdersTable
import com.mati.model.Order
import com.mati.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.math.BigDecimal
import java.time.LocalDateTime

//class OrderPostgresRepo(private val db: Database) {
//
//    suspend fun createOrder(order: Order): Order {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            val orderDAO = OrderDao.new {
//                orderDate = order.orderDate
//                orderStatus = order.orderStatus
//                totalAmount = order.totalAmount
//                userId = order.userId
//            }
//            daoToModel(orderDAO)
//        }
//    }
//
//    suspend fun getOrderById(id: Int): Order? {
//        return newSuspendedTransaction(Dispatchers.IO) {
//            OrderDao.findById(id)?.let { daoToModel(it) }
//        }
//    }
//
//    // Map DAO to Model
//    private fun daoToModel(dao: OrderDao) = Order(
//        dao.id.value,
//        dao.orderDate,
//        dao.orderStatus,
//        dao.totalAmount,
//        dao.userId
//    )
//}

class OrderPostgresRepo : OrderRepository {

    override suspend fun createOrder(orderDate: LocalDateTime?, orderStatus: String?, totalAmount: BigDecimal, userId: Int): Order? {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderDao.new {
                this.orderDate = orderDate
                this.orderStatus = orderStatus
                this.totalAmount = totalAmount
                this.userId = userId
            }.toModel()
        }
    }

    override suspend fun getOrderById(orderId: Int): Order? {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderDao.findById(orderId)?.toModel()
        }
    }

    override suspend fun getOrdersByUserId(userId: Int): List<Order> {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderDao.find { OrdersTable.userId eq userId }
                .map { it.toModel() }
        }
    }

    override suspend fun updateOrderStatus(orderId: Int, newStatus: String): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderDao.findById(orderId)?.let {
                it.orderStatus = newStatus
                true
            } ?: false
        }
    }

    override suspend fun deleteOrder(orderId: Int): Boolean {
        return newSuspendedTransaction(Dispatchers.IO) {
            OrderDao.findById(orderId)?.let {
                it.delete()
                true
            } ?: false
        }
    }

    private fun OrderDao.toModel() = Order(
        id.value,
        orderDate,
        orderStatus,
        totalAmount,
        userId
    )
}

