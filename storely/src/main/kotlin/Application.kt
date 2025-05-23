package com.mati

import com.mati.model.Favorite
import com.mati.plugins.configureDatabases
import com.mati.plugins.configureRouting
import com.mati.plugins.routings.*
import com.mati.repositories.FavoriteRepository
import com.mati.repositories.PaymentRepository
import com.mati.repositories.impl.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*


fun main(args: Array<String>) {
//    io.ktor.server.netty.EngineMain.main(args)
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    embeddedServer(Netty, port = port, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation){
        json()
    }

    val productRepository=  ProductPostgresRepo()
    val userRepository = UserPostgresRepo()


    configureProductRouting(productRepository)
    configureUserRouting(userRepository)
    configureDatabases()
    configureRouting()
}

