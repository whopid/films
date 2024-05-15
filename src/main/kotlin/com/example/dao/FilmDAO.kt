package com.example.dao

import com.example.apiHandler.CreateFilmRequest
import com.example.apiHandler.GetFilmResponse

interface FilmDAO {
    suspend fun findById(id: Int): GetFilmResponse
    suspend fun getAll(): List<GetFilmResponse>
    suspend fun add(film: CreateFilmRequest): Int
    suspend fun removeById(id: Int)
    suspend fun findByAuthor(author: String):List<GetFilmResponse>
}