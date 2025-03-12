package com.mati

import com.mati.plugins.configureDatabases
import com.mati.plugins.configureRouting
import com.mati.plugins.routings.configureProductRouting
import com.mati.repositories.impl.ProductPostgresRepo
import io.ktor.server.application.*

fun main(args: Array<String>) {


    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
    val productrepository = ProductPostgresRepo()
//    configureSerialization()
    configureProductRouting(productrepository)
    configureDatabases()
    configureRouting()

}
