package com.mati.repositories.impl


import com.mati.dao.UserDao
import com.mati.model.User
import com.mati.repositories.UserRepository

class UserPostgresRepo : UserRepository{
    override suspend fun createUser(
        name: String,
        email: String,
        password: String,
        address: String,
        phone: String
    ): UserDao {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: Int): UserDao? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByEmail(email: String): UserDao? {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<UserDao> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(
        id: Int,
        name: String,
        email: String,
        password: String,
        address: String,
        phone: String
    ): UserDao? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun userExistsByEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }

    fun daoToModel(dao: UserDao) = User(
        dao.id.value,
        dao.name,
        dao.email,
        dao.password,
        dao.address,
        dao.phone
    )

}





