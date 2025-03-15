package com.mati.plugins.routings

import com.mati.model.Product
import com.mati.repositories.PaymentRepository
import com.mati.repositories.ProductRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get


