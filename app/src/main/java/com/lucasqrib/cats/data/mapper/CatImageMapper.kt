package com.lucasqrib.cats.data.mapper

import com.lucasqrib.cats.data.dto.CatImageNetworkDTO
import com.lucasqrib.cats.domain.model.CatImage
import com.lucasqrib.cats.data.mapper.Mapper

object CatImageMapper : Mapper<CatImageNetworkDTO, CatImage> {
    override fun invoke(dto: CatImageNetworkDTO): CatImage = CatImage(
        id = dto.id,
        width = dto.width,
        height = dto.height,
        url = dto.url
    )
}