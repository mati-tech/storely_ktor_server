package com.mati.plugins.routings

import com.mati.dao.UserDao
import com.mati.model.User
import com.mati.repositories.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUserRouting(repository: UserRepository) {
    routing {
        route("/users") {
            // Get all users
            get {
                val users = repository.getAllUsers()
                call.respond(users)
            }

            // Get user by ID
            get("/{userId}") {
                val userId = call.parameters["userId"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                    return@get
                }
                val user = repository.getUserById(userId)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                } else {
                    call.respond(user)
                }
            }

            // Get user by email
            get("/email") {
                val email = call.request.queryParameters["email"]
                if (email.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Email query parameter is required")
                    return@get
                }
                val user = repository.getUserByEmail(email)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                } else {
                    call.respond(user)
                }
            }

            // Create user
            post {
                try {
                    val user = call.receive<User>()
                    repository.createUser(user)
                    call.respond(HttpStatusCode.Created)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user data")
                }
            }

            // Update user
            put("/{userId}") {
                val userId = call.parameters["userId"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                    return@put
                }

                // Fetch the existing user before updating
                val existingUser = repository.getUserById(userId)
                if (existingUser == null) {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                    return@put
                }

                try {
                    // Receive the updated user data
                    val user = call.receive<User>()

                    // Update the user using the repository method
                    val updatedUser = repository.updateUser(userId, user)

                    // Respond based on the result
                    if (updatedUser == null) {
                        call.respond(HttpStatusCode.NotFound, "User not found")
                    } else {
                        call.respond(HttpStatusCode.OK, updatedUser)  // Respond with the updated user model
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user data: ${e.message}")
                }
            }


            // Delete user
            delete("/{userId}") {
                val userId = call.parameters["userId"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                    return@delete
                }
                if (repository.deleteUser(userId)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                }
            }
        }
    }
}