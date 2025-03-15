package com.mati.repositories.impl

import com.mati.dao.ProductDao
import com.mati.dao.UserDao
import com.mati.db.UsersTable
import com.mati.model.Product
import com.mati.model.User
import com.mati.repositories.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UserPostgresRepo : UserRepository {

    // Create a new User
    override suspend fun createUser(user: User): User {
        return transaction {
            UserDao.new {
                name = user.name
                email = user.email
                password = user.password
                address = user.address
                phone =  user.phone
            }.toUser()
        }
    }

    // Get a user by ID
    override suspend fun getUserById(id: Int): User? {
        return transaction {
            UserDao.findById(id)?.toUser()
        }
    }

    // Get a user by email
    override suspend fun getUserByEmail(email: String): User? {
        return transaction {
            UserDao.find { UsersTable.email eq email }.singleOrNull()?.toUser()
        }
    }

    // Get all users
    override suspend fun getAllUsers(): List<User> {
        return transaction {
            UserDao.all().map { it.toUser() }
        }
    }

    // Update a user
    override suspend fun updateUser(id: Int, user:User ): Boolean {
        return transaction {
            val existingUser = UserDao.findById(id) ?: return@transaction false
            existingUser.apply {
                name = user.name
                email = user.email
                password = user.password
                address = user.address
                phone = user.phone
            }
            true
        }
    }

    // Delete a user by ID
    override suspend fun deleteUser(id: Int): Boolean {
        return transaction {
            val user = UserDao.findById(id) ?: return@transaction false
            user.delete()
            true
        }
    }

    // Check if a user exists by email
    override suspend fun userExistsByEmail(email: String): Boolean {
        return transaction {
            UserDao.find { UsersTable.email eq email }.count() > 0
        }
    }

    private fun UserDao.toUser() = User(
        id = id.value,
        name = name,
        email = email,
        password = password,
        address = address,
        phone = phone
    )
}