package com.mati

import com.mati.db.configureDatabases
import com.mati.plugins.configureRouting
import com.mati.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureRouting()
}
