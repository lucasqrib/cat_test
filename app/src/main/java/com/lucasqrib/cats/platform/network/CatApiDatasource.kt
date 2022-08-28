package com.lucasqrib.cats.platform.network

import com.lucasqrib.cats.data.datasource.CatDatasource
import com.lucasqrib.cats.data.dto.CatNetworkDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CatApiDatasource : CatDatasource {

    @GET("v1/breeds")
    override suspend fun getCatList(
        @Query("limit") size: Int,
        @Query("page") page: Int,
        @Query("order") order: String
    ): Response<List<CatNetworkDTO>>
}