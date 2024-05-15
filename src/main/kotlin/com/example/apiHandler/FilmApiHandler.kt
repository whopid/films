package com.example.apiHandler

import com.example.dao.FilmDAOInMemory
import com.example.service.FilmService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

data class GetFilmResponse(
    val id: Int,
    val name: String,
    val author: String,
    val rating: Float,
    val durationSec: Int
)

data class CreateFilmRequest(
    val name: String,
    val author: String,
    val rating: Float,
    val durationSec: Int
) {
    init {
        if (rating > 10) throw BadRequestException("rating can't be more than 10")
        if (rating < 0) throw BadRequestException("rating can't be less than 0")
    }
}

fun Application.configureFilmRoutes() {
    val filmService = FilmService(FilmDAOInMemory())
    routing {
        route("films") {
            get {
                call.respond(HttpStatusCode.OK, filmService.getAll())
            }
            get("{id}") {
                val id = call.parameters["id"]?.toInt() ?: throw BadRequestException("id not provided")
                call.respond(HttpStatusCode.OK, filmService.findById(id))
            }
            post {
                val request: CreateFilmRequest = call.receive()
                val createdId = filmService.add(request)
                call.respond(HttpStatusCode.OK, createdId)
            }
            delete("{id}") {
                val id = call.parameters["id"]?.toInt() ?: throw BadRequestException("id not provided")
                call.respond(HttpStatusCode.OK, filmService.removeById(id))
            }
            get("findByAuthor/{author}") {
                val author = call.parameters["author"] ?: throw BadRequestException("author not provided")
                call.respond(HttpStatusCode.OK, filmService.findByAuthor(author))
            }
        }
    }
}
