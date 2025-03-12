package com.mati.dao

import com.mati.db.PaymentsTable
import com.mati.model.Payment
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PaymentDao(id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<PaymentDao>(PaymentsTable)

    var paymentDate by PaymentsTable.paymentDate
    var orderId by PaymentsTable.orderId
    var paymentMethod by PaymentsTable.paymentMethod
    var paymentstatus by PaymentsTable.paymentStatus
    // Map DAO to Model

}