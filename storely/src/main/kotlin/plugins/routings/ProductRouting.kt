package com.mati.plugins.routings

import com.mati.model.Product
import com.mati.repositories.ProductRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Application.configureProductRouting(repository: ProductRepository) {
    routing {
        route("/products") {
            // Get all products
            get {
                val products = repository.findAll() // Use findAll() here
                call.respond(products)
                return@get
            }

            get("/byId/{productId}") {
                val productId = call.parameters["productId"]?.toIntOrNull()
                if (productId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val product = repository.findById(productId)
                if (product == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(product)
            }

            get("/search") {
                val name = call.request.queryParameters["name"]
                if (name.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Name query parameter is required")
                    return@get
                }
                val products = repository.findByName(name)
                if (products.isEmpty()) {
                    call.respond(HttpStatusCode.NotFound, "No products found")
                    return@get
                }
                call.respond(products)
            }


            post {
                try {
                    val product = call.receive<Product>()
                    repository.add(product)
                    call.respond(HttpStatusCode.Created)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{productId}") {
                val productId = call.parameters["productId"]?.toIntOrNull()
                if (productId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.deleteById(productId)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }

}
