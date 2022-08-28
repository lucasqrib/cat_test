package com.lucasqrib.cats.data.repository

import com.lucasqrib.cats.data.datasource.CatDatasource
import com.lucasqrib.cats.data.mapper.CatMapper
import com.lucasqrib.cats.domain.model.Cat
import com.lucasqrib.cats.domain.model.EmptyResultException
import com.lucasqrib.cats.domain.repository.CatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val PAGE_SIZE = 11

class CatsRepositoryImpl @Inject constructor(
    private val datasource: CatDatasource,
    private val dispatcher: CoroutineDispatcher
) : CatsRepository {

    override suspend fun getCatList(page: Int): Result<List<Cat>> {
        return withContext(dispatcher) {
            datasource.getCatList(PAGE_SIZE, page).run {
                if (isSuccessful) {
                    this.body()?.map { dto -> CatMapper(dto) }
                        ?.let { catList -> Result.success(catList) } ?: Result.failure(
                        EmptyResultException
                    )
                } else {
                    Result.failure(Throwable(errorBody()?.toString()))
                }
            }

        }
    }
}