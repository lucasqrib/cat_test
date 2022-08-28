package com.lucasqrib.cats.data.datasource

import com.lucasqrib.cats.data.dto.CatNetworkDTO
import retrofit2.Response

interface CatDatasource {
    suspend fun getCatList(
        size: Int,
        page: Int,
        order: String = "ASC"
    ): Response<List<CatNetworkDTO>>
}