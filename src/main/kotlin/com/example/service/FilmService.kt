package com.example.service

import com.example.apiHandler.CreateFilmRequest
import com.example.apiHandler.GetFilmResponse
import com.example.dao.FilmDAO

class FilmService(private val filmDao: FilmDAO) {
    suspend fun findById(id: Int): GetFilmResponse = filmDao.findById(id)
    suspend fun getAll(): List<GetFilmResponse> = filmDao.getAll()
    suspend fun add(film: CreateFilmRequest): Int = filmDao.add(film)
    suspend fun removeById(id:Int) = filmDao.removeById(id)
}