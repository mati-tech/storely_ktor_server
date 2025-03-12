package com.mati.repositories

import com.mati.dao.UserDao

// Define the repository interface for the User entity
interface UserRepository {

    // Create a new User
    suspend fun createUser(name: String, email: String, password: String, address: String, phone: String): UserDao

    // Get a user by ID
    suspend fun getUserById(id: Int): UserDao?

    // Get a user by email
    suspend fun getUserByEmail(email: String): UserDao?

    // Get all users
    suspend fun getAllUsers(): List<UserDao>

    // Update a user
    suspend fun updateUser(id: Int, name: String, email: String, password: String, address: String, phone: String): UserDao?

    // Delete a user by ID
    suspend fun deleteUser(id: Int): Boolean

    // Check if a user exists by email
    suspend fun userExistsByEmail(email: String): Boolean
}