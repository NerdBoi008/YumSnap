package com.example.yumsnap.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

    private val serialize = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val client: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json = serialize)
        }
    }

    fun closeClient() {
        client.close()
    }
}