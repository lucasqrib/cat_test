package com.lucasqrib.cats.domain.usecase

import com.lucasqrib.cats.domain.model.Cat

interface RetrieveCatsUseCase {
    suspend operator fun invoke(page: Int): Result<List<Cat>>
}