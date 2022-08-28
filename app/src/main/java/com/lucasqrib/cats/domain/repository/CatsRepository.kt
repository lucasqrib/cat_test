package com.lucasqrib.cats.domain.repository

import com.lucasqrib.cats.domain.model.Cat

interface CatsRepository {
    suspend fun getCatList(page: Int): Result<List<Cat>>
}