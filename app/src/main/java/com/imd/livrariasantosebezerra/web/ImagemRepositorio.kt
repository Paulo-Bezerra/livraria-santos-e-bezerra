package com.imd.livrariasantosebezerra.web

import com.imd.livrariasantosebezerra.web.resposta.ImagemResposta
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ImagemRepositorio {
  companion object {
    suspend fun buscarImagem(isbn: String): String {
      return try {
        val httpClient = HttpClient(Android) {
          install(Logging) {
            level = LogLevel.ALL
          }
          install(ContentNegotiation) {
            json(Json {
              ignoreUnknownKeys = true
            })
          }
        }
        val resposta: Map<String, ImagemResposta> =
          httpClient.get("https://openlibrary.org/api/books?bibkeys=ISBN:${isbn}&format=json&jscmd=viewapi")
            .body()
        resposta.values.firstOrNull()?.urlImagem() ?: ""
      } catch (e: Exception) {
        ""
      }
    }
  }

}