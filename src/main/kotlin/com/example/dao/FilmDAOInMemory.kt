package com.example.dao

import com.example.apiHandler.CreateFilmRequest
import com.example.apiHandler.GetFilmResponse
import io.ktor.server.plugins.*

class FilmDAOInMemory : FilmDAO {
    private val filmStorage = mutableListOf<GetFilmResponse>()
    override suspend fun findById(id: Int): GetFilmResponse {
        return filmStorage.find { it.id == id } ?: throw NotFoundException("Film with such id $id not found")
    }

    override suspend fun getAll(): List<GetFilmResponse> = filmStorage

    override suspend fun add(film: CreateFilmRequest): Int {
        val newFilm = GetFilmResponse(
            id = filmStorage.size,
            name = film.name,
            author = film.author,
            rating = film.rating,
            durationSec = film.durationSec
        )
        filmStorage.add(newFilm)
        return newFilm.id
    }

    override suspend fun removeById(id: Int) {
        filmStorage.removeAt(id)
    }

    override suspend fun findByAuthor(author: String): List<GetFilmResponse> {
        return filmStorage.filter { it.author == author }
    }
}