package com.mati.dao

import com.mati.db.UsersTable
import com.mati.model.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDao>(UsersTable)

    var name by UsersTable.name
    var email by UsersTable.email
    var password by UsersTable.password
    var address by UsersTable.address
    var phone by UsersTable.phone
}

// Extension function to convert DAO to model
