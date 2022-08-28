package com.lucasqrib.cats.domain.usecase

import com.lucasqrib.cats.domain.model.Cat
import com.lucasqrib.cats.domain.repository.CatsRepository
import javax.inject.Inject

class RetrieveCatsUseCaseImpl @Inject constructor(
    private val repository: CatsRepository
) : RetrieveCatsUseCase {
    override suspend fun invoke(page: Int): Result<List<Cat>> {
        return try {
            repository.getCatList(page)
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}